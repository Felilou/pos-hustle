package ormapping;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(force=true, access = AccessLevel.PROTECTED)
@Getter
@Setter
public class CourseSubscription extends AbstractPersistable<Long> {

    @ManyToOne(cascade = CascadeType.PERSIST)
    @NotNull
    private Attendee attendee;

    @NotNull
    private LocalDateTime subscriptionTimestamp;

    @OneToMany(cascade = CascadeType.PERSIST)
    @Singular
    private final List<Attendance> attendances;

    @NotNull
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Course course;

    @Builder
    protected CourseSubscription(Attendee attendee, LocalDateTime subscriptionTimestamp, Course course, @Singular List<Attendance> attendances){
        this.attendee = attendee;
        this.subscriptionTimestamp = subscriptionTimestamp;
        this.course = course;
        this.attendances = new ArrayList<>(attendances);
    }
}
