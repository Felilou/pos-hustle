package ft_restfulapi.domain;

import jakarta.validation.constraints.NotNull;

public record Key(
        @NotNull String value
) {
}
