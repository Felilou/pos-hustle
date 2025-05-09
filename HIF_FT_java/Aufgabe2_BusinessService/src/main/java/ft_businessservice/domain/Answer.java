package ft_businessservice.domain;

import jakarta.validation.constraints.NotNull;

public record Answer(
        @NotNull Question question,
        @NotNull PossibleAnswer possibleAnswer
) {
}
