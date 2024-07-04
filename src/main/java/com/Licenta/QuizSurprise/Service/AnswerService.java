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

    public boolean checkAnswers(Integer questionId, List<Integer> answerIds) {
        List<Answer> correctAnswers = answerRepository.findAllByQuestionIdAndIsCorrect(questionId, true);

        // Verificăm dacă lista de răspunsuri furnizată conține toate răspunsurile corecte
        for (Answer correctAnswer : correctAnswers) {
            if (!answerIds.contains(correctAnswer.getId())) {
                return false;
            }
        }

        // Verificăm dacă lista furnizată conține doar răspunsuri corecte
        for (Integer answerId : answerIds) {
            Answer answer = answerRepository.findById(answerId).orElse(null);
            if (answer == null || !answer.getIsCorrect() || !answer.getQuestion().getId().equals(questionId)) {
                return false;
            }
        }

        return true;
    }
}
