package ormapping.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Getter
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
    }

    public Set<Course> getCourses() {
        return Collections.unmodifiableSet(courses);
    }

    public void addCourse(@NotNull Course course) {
        Objects.requireNonNull(course);
        if (courses.add(course)) {
            course.setHeldBy(this);
        }
    }

    public void removeCourse(@NotNull Course course) {
        Objects.requireNonNull(course);
        courses.removeIf(c -> c == course);
    }

}
