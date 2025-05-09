package ft_businessservice.domain;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record Attendee(
        @NotNull String name,
        @NotNull LocalDate birthDate
) {
}
