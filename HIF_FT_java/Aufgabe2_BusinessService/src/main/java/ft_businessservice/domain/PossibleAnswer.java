package ft_businessservice.domain;

import jakarta.validation.constraints.NotNull;

public record PossibleAnswer(
        @NotNull String text,
        @NotNull Integer points
) {
}
