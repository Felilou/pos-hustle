package ft_restfulapi.service;

import ft_restfulapi.domain.Exam;
import ft_restfulapi.commands.UpdateThresholdsCommand;

import java.security.Key;
import java.util.List;

public interface ExamService {
    List<Exam> getExams(Key key, boolean includeAnswers);
    Exam updateExamThresholds(Key key, UpdateThresholdsCommand cmd);
}
