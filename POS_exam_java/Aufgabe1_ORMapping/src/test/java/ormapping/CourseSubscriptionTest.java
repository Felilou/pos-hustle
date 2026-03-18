package ormapping;

import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;
import ormapping.model.CourseSubscription;
import ormapping.repositories.CourseSubscriptionRepository;

@SpringBootTest
public class CourseSubscriptionTest extends SimpleEntityPersistanceTest<CourseSubscription> {


    @Autowired
    public CourseSubscriptionTest(CourseSubscriptionRepository repository, Validator validator) {
        super(repository, validator);
    }

    @Override
    public Iterable<CourseSubscription> provideValidEntities() {

    }

    @Override
    public Iterable<CourseSubscription> provideInvalidEntities() {
        return null;
    }

    @Override
    void perform_additional_good_case_tests(CourseSubscription persistedEntity) {

    }

    @Override
    void perform_additional_bad_case_tests(CourseSubscription notPersistedEntity) {

    }
}
