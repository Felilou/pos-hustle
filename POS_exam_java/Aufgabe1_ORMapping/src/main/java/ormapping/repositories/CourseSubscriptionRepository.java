package ormapping.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ormapping.dto.CourseSubscriptionDTO;
import ormapping.model.CourseSubscription;

import java.util.List;

@Repository
public interface CourseSubscriptionRepository extends JpaRepository<CourseSubscription, Long> {

    @Query("SELECT new ormapping.dto.CourseSubscriptionDTO(cs.course.name, cs.course.beginDate, cs.attendee.firstName.value, cs.attendee.lastName.value) FROM CourseSubscription cs WHERE cs.course.heldBy = :speaker")
    List<CourseSubscriptionDTO> getCourseSubscriptionByCoursesHeldBySpeaker();

}
