package ormapping;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@SpringBootTest
@Transactional
class SpeakerCourseRelationTest {

    private Speaker speaker1;
    private Speaker speaker2;
    private Course course;

    @PersistenceContext
    private EntityManager em;

    @BeforeEach
    void setUp() {
        speaker1 = Speaker.builder()
                .firstName(FirstName.builder().value("Max").build())
                .lastName(LastName.builder().value("Mustermann").build())
                .email(Email.builder().email("max@example.com").build())
                .primaryTopic(Topic.SOFTWARE_ENGINEERING)
                .secondaryTopic(Topic.SOFTWARE_ENGINEERING)
                .build();

        em.persist(speaker1);

        log.info("Speaker created {}", speaker1);

        speaker2 = Speaker.builder()
                .firstName(FirstName.builder().value("Anna").build())
                .lastName(LastName.builder().value("Meyer").build())
                .email(Email.builder().email("anna@example.com").build())
                .primaryTopic(Topic.SOFTWARE_ENGINEERING)
                .secondaryTopic(Topic.SOFTWARE_ENGINEERING)
                .build();

        log.info("Speaker created {}", speaker2);

        em.persist(speaker2);

        course = Course.builder()
                .name("Java Basics")
                .description("Intro to Java")
                .covers(Topic.SOFTWARE_ENGINEERING)
                .beginDate(LocalDate.now())
                .heldBy(speaker1)
                .build();

        em.persist(course);

        log.info("Course created {}", course);

        em.flush();

    }

    @Test
    void addCourse_setsHeldByOnCourse() {
        // Der Kurs wurde über den Builder mit speaker1 verknüpft
        assertThat(course.getHeldBy()).isEqualTo(speaker1);
    }

    @Test
    void addCourse_addsCourseToSpeaker() {
        // speaker1 soll den Kurs in seiner Liste haben
        assertThat(speaker1.getCourses()).contains(course);
    }

    @Test
    void setSpeaker_switchesSpeaker() {
        // Kurs von speaker1 auf speaker2 wechseln
        course.setSpeaker(speaker2);

        assertThat(course.getHeldBy()).isEqualTo(speaker2);
        assertThat(speaker2.getCourses()).contains(course);
    }

    @Test
    void setSpeaker_removesFromOldSpeaker() {
        // Nach dem Wechsel darf speaker1 den Kurs nicht mehr haben
        course.setSpeaker(speaker2);

        assertThat(speaker1.getCourses()).doesNotContain(course);
    }

    @Test
    void removeCourse_withoutReplacement_throws() {
        assertThatThrownBy(() -> speaker1.removeCourse(course))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("kein neuer Speaker");
    }

    @Test
    void setSpeaker_sameSpearker_noChange() {
        // Nochmals denselben Speaker setzen — keine Änderung
        course.setSpeaker(speaker1);

        assertThat(course.getHeldBy()).isEqualTo(speaker1);
        assertThat(speaker1.getCourses()).containsOnlyOnce(course);
    }

    @Test
    void addCourse_toNewSpeaker_removesFromOldSpeaker() {
        // Kurs über speaker2.addCourse hinzufügen soll speaker1 sauber aufräumen
        speaker2.addCourse(course);

        assertThat(course.getHeldBy()).isEqualTo(speaker2);
        assertThat(speaker2.getCourses()).contains(course);
        assertThat(speaker1.getCourses()).doesNotContain(course);
    }

}
