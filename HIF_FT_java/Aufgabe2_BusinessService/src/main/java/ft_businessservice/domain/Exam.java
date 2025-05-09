package ft_businessservice.domain;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record Exam(
        @NotNull String name,
        @NotNull Integer repeatThreshold,
        @NotNull Integer failThresholdx,
        @NotNull List<Question> questions
) {
}
