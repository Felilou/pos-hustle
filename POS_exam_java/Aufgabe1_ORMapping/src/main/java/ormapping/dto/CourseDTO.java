package ormapping.dto;

import ormapping.model.Topic;

import java.time.LocalDate;

public record CourseDTO(
        String name,
        LocalDate beginDate,
        String speakerName
) {
}
