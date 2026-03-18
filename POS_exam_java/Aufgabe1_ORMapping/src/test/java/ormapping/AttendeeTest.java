package ormapping;

import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ormapping.model.Attendee;
import ormapping.model.Email;
import ormapping.model.FirstName;
import ormapping.model.LastName;
import ormapping.repositories.AttendeeRepository;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class AttendeeTest {

    Attendee attendee;

    @Autowired
    private AttendeeRepository repository;

    @Autowired
    Validator validator;
    
    @BeforeEach
    void setUp() {
        attendee = Attendee.builder()
                .key("attendee_1")
                .email(Email.builder().email("attendee@test.at").build())
                .lastName(LastName.builder().value("Huber").build())
                .firstName(FirstName.builder().value("Felix").build())
                .build();
    }

    @Test
    void test_if_db_mapping_is_correct(){

        repository.saveAndFlush(attendee);

        Long id = attendee.getId();

        Attendee read = repository.getReferenceById(id);

        assertFalse(read.isNew());

        assertEquals(read, attendee);
        assertThat(attendee).usingRecursiveComparison().isEqualTo(read);

        Set<ConstraintViolation<Attendee>> violations = validator.validate(read);
        assertTrue(violations.isEmpty());

    }

}
