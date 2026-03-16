package ormapping;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Course extends AbstractPersistable<Long> {

    @Column(name="course_name", unique = true)
    @NotNull
    private String name;

    @NotNull
    private String description;

    @ManyToOne(cascade = CascadeType.PERSIST, optional = false)
    @JoinColumn(nullable = false)
    @NotNull
    @Setter(AccessLevel.NONE)
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

        setSpeaker(heldBy);
    }

    public void setSpeaker(Speaker newSpeaker) {
        if (newSpeaker == null) {
            throw new IllegalArgumentException("heldBy darf nicht null sein");
        }
        if (this.heldBy == newSpeaker) {
            return;
        }

        Speaker oldSpeaker = this.heldBy;
        this.heldBy = newSpeaker;

        if (oldSpeaker != null) {
            oldSpeaker.removeCourseInternal(this);
        }
        newSpeaker.addCourseInternal(this);
    }

    public void removeSpeaker() {
        throw new IllegalStateException("Ein Course muss immer einem Speaker zugeordnet sein");
    }

}