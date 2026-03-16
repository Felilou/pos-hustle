package ormapping;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Embeddable
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FirstName {

    @Length(min=0, max=50)
    @Pattern(regexp = "^[a-zA-ZäöüÄÖÜß .\\-]+$")
    @NotNull
    private String value;

    @Builder(access = AccessLevel.PUBLIC)
    protected FirstName(String value) {
        this.value = value;
    }

}
