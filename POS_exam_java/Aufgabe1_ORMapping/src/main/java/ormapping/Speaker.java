package ormapping;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Getter
@Setter
public class Speaker extends User{

    @NotNull
    @Enumerated(EnumType.STRING)
    private Topic primaryTopic;

    @OneToMany(mappedBy = "heldBy", cascade = CascadeType.PERSIST)
    @Getter(AccessLevel.NONE)
    private final Set<Course> courses = new HashSet<>();

    @NotNull
    @Enumerated(EnumType.STRING)
    private Topic secondaryTopic;

    @Builder(access = AccessLevel.PUBLIC)
    protected Speaker(LastName lastName, FirstName firstName, Email email, Topic primaryTopic, Topic secondaryTopic, @Singular Set<Course> courses) {
        super(lastName, firstName, email);
        this.primaryTopic = primaryTopic;
        this.secondaryTopic = secondaryTopic;

        if (courses != null) {
            courses.forEach(this::addCourse);
        }
    }

    public Set<Course> getCourses() {
        return Collections.unmodifiableSet(courses);
    }

    public void addCourse(@NotNull Course course) {
        if (course == null) return;
        course.setSpeaker(this);
    }

    public void removeCourse(@NotNull Course course) {
        if (course == null) return;
        if (course.getHeldBy() == this) {
            throw new IllegalStateException("Course kann nicht entfernt werden, solange kein neuer Speaker gesetzt wurde");
        }
        courses.remove(course);
    }

    // package-private: nur von Course.setSpeaker aufgerufen
    void addCourseInternal(Course course) {
        courses.add(course);
    }

    // package-private: nur von Course.setSpeaker aufgerufen
    void removeCourseInternal(Course course) {
        courses.remove(course);
    }

}
