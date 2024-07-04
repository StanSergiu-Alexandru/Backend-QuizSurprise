package com.Licenta.QuizSurprise.Controller;

import com.Licenta.QuizSurprise.DTO.AnswerCheckDTO;
import com.Licenta.QuizSurprise.DTO.QuestionDTO;
import com.Licenta.QuizSurprise.Entity.Question;
import com.Licenta.QuizSurprise.Service.AnswerService;
import com.Licenta.QuizSurprise.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private AnswerService answerService;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/generateQuestion")
    public ResponseEntity<Question> generateQuestion(@RequestParam String subjectType) {
        Question question = questionService.getRandomQuestionBySubjectType(subjectType);

        if (question == null) {
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(question);
    }

    @PostMapping("/check-answers/{questionId}")
    public ResponseEntity<Boolean> checkAnswers(@PathVariable Integer questionId, @RequestBody AnswerCheckDTO answerCheckDTO) {
        boolean areCorrect = answerService.checkAnswers(questionId, answerCheckDTO.getAnswers());
        return ResponseEntity.ok(areCorrect);
    }

    @PostMapping("/add-question")
    public ResponseEntity<Question> addQuestion(@RequestBody QuestionDTO questionDTO) {
        Question question = questionService.addQuestion(questionDTO);
        return ResponseEntity.ok(question);
    }
}
