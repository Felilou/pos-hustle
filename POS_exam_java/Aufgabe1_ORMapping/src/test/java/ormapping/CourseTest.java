package ormapping;

import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ormapping.model.*;
import ormapping.repositories.CourseRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
public class CourseTest{

    Course course;
    Speaker speaker;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    Validator validator;

    @BeforeEach
    void setUp() {
        speaker =
                Speaker.builder()
                        .firstName(FirstName.builder().value("Felix").build())
                        .lastName(LastName.builder().value("Huber").build())
                        .email(Email.builder().email("test@test.at").build())
                        .primaryTopic(Topic.SOFTWARE_ENGINEERING)
                        .secondaryTopic(Topic.SOFTWARE_ENGINEERING)
                        .build();

        course =
                Course.builder()
                        .name("test")
                        .heldBy(speaker)
                        .description("test course")
                        .beginDate(LocalDate.now())
                        .covers(Topic.SOFTWARE_ENGINEERING)
                        .build();
    }

    @Test
    void test_persist(){
        Course ret = courseRepository.saveAndFlush(course);

        assertFalse(ret.isNew());

        assertFalse(speaker.isNew());

        Set<ConstraintViolation<Course>> courseViolations = validator.validate(course);
        assertTrue(courseViolations.isEmpty());
    }


}
