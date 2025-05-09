package ft_businessservice.domain;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record Attendance(
        @NotNull Exam exam,
        @NotNull Attendee attendee,
        @NotNull List<Answer> answers
) {
}
