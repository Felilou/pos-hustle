package ormapping.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Course extends AbstractPersistable<Long> {

    @Column(name="course_name", unique = true)
    @NotNull
    private String name;

    @NotNull
    private String description;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(nullable = false)
    @NotNull
    private Speaker heldBy;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Topic covers;

    @NotNull
    private LocalDate beginDate;

    @Builder
    protected Course(String name, String description, Speaker heldBy, Topic covers, LocalDate beginDate) {
        this.name = name;
        this.description = description;
        this.covers = covers;
        this.beginDate = beginDate;
        setHeldBy(heldBy);
    }

    public void setHeldBy(@NotNull Speaker speaker) {
        Objects.requireNonNull(speaker);
        if (Objects.equals(this.heldBy, speaker)) return;
        if (this.heldBy != null) {
            this.heldBy.removeCourse(this);
        }
        this.heldBy = speaker;
        if (!speaker.getCourses().contains(this)) {
            speaker.addCourse(this);
        }
    }

}