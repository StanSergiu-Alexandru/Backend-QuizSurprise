package com.Licenta.QuizSurprise.Repository;

import com.Licenta.QuizSurprise.Entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    List<Answer> findAllByQuestionIdAndIsCorrect(Integer questionId, Boolean isCorrect);
    Answer findByQuestionIdAndAnswer(Integer questionId, String answer);
}
