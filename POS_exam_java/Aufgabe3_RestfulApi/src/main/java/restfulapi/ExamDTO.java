package restfulapi;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ExamDTO(
        String key,
        String name,
        @JsonProperty("repeat_threshold")
        int repeatThreshold,
        List<AnswerDTO> answers
) {

    public static ExamDTO fromEntity(Exam exam, boolean includeQuestions) {
        return new ExamDTO(exam.key.value, exam.name, exam.repeatThreshold,
                includeQuestions ? exam.questions.stream()
                        .flatMap(q -> q.possibleAnswers.stream())
                        .map(AnswerDTO::fromAnswer)
                        .toList() : null);
    }


}
