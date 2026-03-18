package restfulapi;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ExamController.EXAM_API_BASE_PATH)
@AllArgsConstructor
public class ExamController {

    public final static String EXAM_API_BASE_PATH = "/exam";

    private final ExamService examService;

    @GetMapping("/{key}")
    public ResponseEntity<ExamDTO> getExam(@PathVariable String key, @RequestParam(defaultValue = "false") boolean includeAnswers) {
        return ResponseEntity.ok(ExamDTO.fromEntity(examService.getExam(key), includeAnswers));
    }

}
