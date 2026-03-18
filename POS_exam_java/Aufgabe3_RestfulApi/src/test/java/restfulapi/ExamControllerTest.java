package restfulapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ExamController.class)
class ExamControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ExamService examService;

    private Exam createTestExam() {
        var key = new Key();
        key.value = "exam1";

        var answer1 = new PossibleAnswer();
        answer1.text = "Richtig";
        answer1.points = 10;

        var answer2 = new PossibleAnswer();
        answer2.text = "Falsch";
        answer2.points = 0;

        var question = new Question();
        question.key = new Key();
        question.key.value = "q1";
        question.position = 1;
        question.text = "Was ist 1+1?";
        question.type = Type.SINGLE_CHOICE;
        question.possibleAnswers = List.of(answer1, answer2);

        var exam = new Exam();
        exam.key = key;
        exam.name = "Mathematik Prüfung";
        exam.repeatThreshold = 60;
        exam.failThreshold = 30;
        exam.questions = List.of(question);

        return exam;
    }

    @Test
    void getExamWithAnswersReturns200() throws Exception {
        var exam = createTestExam();
        when(examService.getExam("exam1")).thenReturn(exam);

        mockMvc.perform(get("/exam/exam1").param("includeAnswers", "true"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.key").value("exam1"))
                .andExpect(jsonPath("$.name").value("Mathematik Prüfung"))
                .andExpect(jsonPath("$.repeat_threshold").value(60))
                .andExpect(jsonPath("$.answers").isArray())
                .andExpect(jsonPath("$.answers.length()").value(2))
                .andExpect(jsonPath("$.answers[0].text").value("Richtig"))
                .andExpect(jsonPath("$.answers[0].points").value(10))
                .andExpect(jsonPath("$.answers[1].text").value("Falsch"))
                .andExpect(jsonPath("$.answers[1].points").value(0));
    }

    @Test
    void getExamWithoutAnswersReturns200() throws Exception {
        var exam = createTestExam();
        when(examService.getExam("exam1")).thenReturn(exam);

        mockMvc.perform(get("/exam/exam1").param("includeAnswers", "false"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.key").value("exam1"))
                .andExpect(jsonPath("$.name").value("Mathematik Prüfung"))
                .andExpect(jsonPath("$.repeat_threshold").value(60))
                .andExpect(jsonPath("$.answers").doesNotExist());
    }

    @Test
    void getExamWithoutParamDefaultsToNoAnswers() throws Exception {
        var exam = createTestExam();
        when(examService.getExam("exam1")).thenReturn(exam);

        // includeAnswers hat defaultValue "false", daher wird ohne Parameter answers=null zurückgegeben
        mockMvc.perform(get("/exam/exam1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.key").value("exam1"))
                .andExpect(jsonPath("$.answers").doesNotExist());
    }
}

