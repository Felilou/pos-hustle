package ormapping;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Attendance extends AbstractPersistable<Long> {

    @NotNull
    @Column(name = "attendance_date")
    private LocalDate date;

    @NotNull
    @Column(name = "attendance_begin_time")
    private LocalTime begin;

    @NotNull
    @Column(name = "attendance_end_time")
    private LocalTime end;

    private String note;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @NotNull
    private Attendee attendee;

    @Builder(access = AccessLevel.PROTECTED)
    protected Attendance(LocalDate date, LocalTime begin, LocalTime end, String note, Attendee attendee) {
        this.date = date;
        this.begin = begin;
        this.end = end;
        this.note = note;
        this.attendee = attendee;
    }
}
