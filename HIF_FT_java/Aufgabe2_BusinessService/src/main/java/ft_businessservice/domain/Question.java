package ft_businessservice.domain;

import jakarta.validation.constraints.NotNull;

import java.lang.reflect.Type;
import java.util.List;

public record Question(
        @NotNull String text,
        @NotNull Type type,
        @NotNull List<PossibleAnswer> answers
) {
}
