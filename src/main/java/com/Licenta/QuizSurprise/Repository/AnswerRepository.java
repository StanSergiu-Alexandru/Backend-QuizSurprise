package com.Licenta.QuizSurprise.Repository;

import com.Licenta.QuizSurprise.Entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {
}
