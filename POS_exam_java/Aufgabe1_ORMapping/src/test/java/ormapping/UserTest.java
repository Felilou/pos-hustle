package ormapping;


import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ormapping.model.*;
import ormapping.repositories.UserRepository;

import java.util.List;

@SpringBootTest
public class UserTest extends SimpleEntityPersistanceTest<User> {

    @Autowired
    public UserTest(UserRepository userRepository, Validator validator) {
        super(userRepository, validator);
    }

    @Override
    public Iterable<User> provideValidEntities() {
        //use faker lib here
        return List.of(
                Speaker.builder()
                        .firstName(FirstName.builder().value("Felix").build())
                        .lastName(LastName.builder().value("Huber").build())
                        .email(Email.builder().email("was1@das.at").build())
                        .primaryTopic(Topic.SOFTWARE_ENGINEERING)
                        .secondaryTopic(Topic.SOFTWARE_ENGINEERING)
                        .build());
    }

    @Override
    public Iterable<User> provideInvalidEntities() {
        return List.of(
                Speaker.builder().firstName(FirstName.builder().value("Felix").build())
                        .lastName(null)
                        .email(Email.builder().email("was6@das.at").build())
                        .primaryTopic(Topic.SOFTWARE_ENGINEERING)
                        .secondaryTopic(Topic.SOFTWARE_ENGINEERING)
                        .build());
    }

    @Override
    void perform_additional_good_case_tests(User persistedEntity) {

    }

    @Override
    void perform_additional_bad_case_tests(User notPersistedEntity) {

    }
}
