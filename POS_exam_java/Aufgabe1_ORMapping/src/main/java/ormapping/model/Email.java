package ormapping.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Email {

    @Column(unique = true)
    @NotNull
    private String email;

    @Builder(access = AccessLevel.PUBLIC)
    protected Email(String email) {
        this.email = email;
    }

}
