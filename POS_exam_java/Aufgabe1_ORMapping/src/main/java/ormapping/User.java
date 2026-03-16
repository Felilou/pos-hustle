package ormapping;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class User extends AbstractPersistable<Long> {

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "last_name"))
    @NotNull
    private LastName lastName;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "first_name"))
    @NotNull
    private FirstName firstName;

    @Embedded
    @NotNull
    private Email email;

    protected User(LastName lastName, FirstName firstName, Email email) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
    }

}
