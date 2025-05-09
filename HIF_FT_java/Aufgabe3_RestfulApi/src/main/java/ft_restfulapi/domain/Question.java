package ft_restfulapi.domain;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record Question(
        @NotNull Key key,
        @NotNull Integer position,
        @NotNull String text,
        @NotNull Type type,
        @NotNull List<PossibleAnswer> possibleAnswers
) {
}
