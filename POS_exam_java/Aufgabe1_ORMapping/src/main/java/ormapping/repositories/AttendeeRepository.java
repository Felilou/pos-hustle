package ormapping.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ormapping.model.Attendee;

@Repository
public interface AttendeeRepository extends JpaRepository<Attendee, Long> {
}
