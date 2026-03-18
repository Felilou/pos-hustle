package ormapping.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ormapping.dto.CourseDTO;
import ormapping.model.Course;
import ormapping.model.Topic;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query("""
            SELECT new ormapping.dto.CourseDTO(
                        c.name,
                        c.beginDate,
                        CONCAT(c.heldBy.firstName.value, ' ', c.heldBy.lastName.value ))
            FROM Course c
            WHERE c.covers =: topic
            AND c.beginDate BETWEEN :minStartDate AND :maxStartDate""")
    List<CourseDTO> getCoursesByTopicAndBeginDateBetween(@Param("topic") Topic topic,
                                                         @Param("minSTartDate") LocalDate minStartDate,
                                                         @Param("maxStartDate") LocalDate maxStartDate);



}
