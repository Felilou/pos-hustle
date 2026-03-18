package ormapping.dto;

import java.time.LocalDate;

public record CourseSubscriptionDTO(
        String courseName,
        LocalDate startDate,
        String attendeeFirstname,
        String attendeeLastname
) {
}
