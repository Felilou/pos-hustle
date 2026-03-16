package ormapping;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Attendee extends User {

    @Column(name = "attendee_key", unique = true)
    @NotNull
    private String key;

    @Builder(access = AccessLevel.PUBLIC)
    protected Attendee(LastName lastName, FirstName firstName, Email email, String key) {
        super(lastName, firstName, email);
        this.key = key;
    }

}
