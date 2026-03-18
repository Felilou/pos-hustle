package ormapping;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class SimpleEntityPersistanceTest<E extends AbstractPersistable<Long>> {

    JpaRepository<E, Long> repository;

    Validator validator;

    @PersistenceContext
    EntityManager em;

    public SimpleEntityPersistanceTest(JpaRepository<E, Long> repository, Validator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public abstract Iterable<E> provideValidEntities();

    public abstract Iterable<E> provideInvalidEntities();

    private E persistAndFlushEntity(E entity){
        log.info("test with entity {}", entity);
        E ret = repository.saveAndFlush(entity);
        em.clear();
        return ret;
    }

    @ParameterizedTest
    @MethodSource("provideValidEntities")
    public void test_persist_good_case(E entity){

        long countBefore = repository.count();

        entity = persistAndFlushEntity(entity);

        Set<ConstraintViolation<E>> violations = validator.validate(entity);
        assertTrue(violations.isEmpty());

        long countAfter = repository.count();

        assertEquals(countBefore+1, countAfter);

        perform_additional_good_case_tests(entity);

    }

    abstract void perform_additional_good_case_tests(E persistedEntity);

    @ParameterizedTest
    @MethodSource("provideInvalidEntities")
    public void test_persist_bad_case(E entity){

        long countBefore = repository.count();

        Set<ConstraintViolation<E>> violations = validator.validate(entity);
        assertFalse(violations.isEmpty());

        assertThrows(ConstraintViolationException.class,
                () -> persistAndFlushEntity(entity));

        long countAfter = repository.count();

        assertEquals(countBefore, countAfter);

        perform_additional_bad_case_tests(entity);

    }

    abstract void perform_additional_bad_case_tests(E notPersistedEntity);

}
