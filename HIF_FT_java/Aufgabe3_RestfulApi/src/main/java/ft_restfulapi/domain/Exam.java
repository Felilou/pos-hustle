package ft_restfulapi.domain;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record Exam(
        @NotNull Key key,
        @NotNull String name,
        @NotNull Integer repeatThreshold,
        @NotNull Integer failThreshold,
        @NotNull List<Question> questions
) {
}
