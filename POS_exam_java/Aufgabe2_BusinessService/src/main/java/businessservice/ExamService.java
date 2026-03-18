package businessservice;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ExamService {

    public Map<ExamGrade, List<ExamResult>> createExamStatistics(Exam exam, List<Attendance> attendances) {
        return attendances.stream()
                .filter(a -> a.exam == exam)
                .collect(Collectors.groupingBy(
                        a -> gradeFor(exam, totalPoints(a)),
                        Collectors.collectingAndThen(
                                Collectors.mapping(
                                        a -> new ExamResult(a.attendee.name, totalPoints(a)),
                                        Collectors.toList()
                                ),
                                list -> list.stream()
                                        .sorted(Comparator.comparingInt(ExamResult::totalPoints).reversed())
                                        .toList()
                        )
                ));
    }

    private int totalPoints(Attendance a) {
        return a.answers.stream()
                .mapToInt(answer -> answer.answer.points)
                .sum();
    }

    private ExamGrade gradeFor(Exam exam, int points) {
        if (points > exam.repeatThreshold) return ExamGrade.PASSED;
        if (points > exam.failThreshold)   return ExamGrade.REPEAT;
        return ExamGrade.FAILED;
    }

}
