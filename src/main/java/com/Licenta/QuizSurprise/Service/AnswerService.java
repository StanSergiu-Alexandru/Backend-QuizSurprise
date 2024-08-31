package com.Licenta.QuizSurprise.Service;

import com.Licenta.QuizSurprise.Entity.Answer;
import com.Licenta.QuizSurprise.Entity.User;
import com.Licenta.QuizSurprise.Repository.AnswerRepository;
import com.Licenta.QuizSurprise.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private UserRepository userRepository;

    public boolean checkAnswers(Integer userId, Integer questionId, List<Integer> answerIds) {
        List<Answer> correctAnswers = answerRepository.findAllByQuestionIdAndIsCorrect(questionId, true);
        Optional<User> user = userRepository.findById(userId);

        if(user.isEmpty()) {
            return false;
        }

        user.get().setLastAccessed(LocalDate.now());
        userRepository.save(user.get());

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
