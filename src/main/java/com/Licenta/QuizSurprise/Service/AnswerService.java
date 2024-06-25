package com.Licenta.QuizSurprise.Service;

import com.Licenta.QuizSurprise.Entity.Answer;
import com.Licenta.QuizSurprise.Repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    public boolean checkAnswers(Integer questionId, List<String> answerTexts) {
        List<Answer> correctAnswers = answerRepository.findAllByQuestionIdAndIsCorrect(questionId, true);

        // Verific dacă lista de raspunsuri furnizată contine toate raspunsurile corecte
        for (Answer correctAnswer : correctAnswers) {
            if (!answerTexts.contains(correctAnswer.getAnswer())) {
                return false;
            }
        }

        // Verific ddaca fiecare raspuns din lista furnizata este corect.
        // Caut in DB fiecare raspuns => daca nu exista sau daca e 'false' => return false
        for (String answerText : answerTexts) {
            Answer answer = answerRepository.findByQuestionIdAndAnswer(questionId, answerText);
            if (answer == null || !answer.getIsCorrect()) {
                return false;
            }
        }

        return true;
    }
}
