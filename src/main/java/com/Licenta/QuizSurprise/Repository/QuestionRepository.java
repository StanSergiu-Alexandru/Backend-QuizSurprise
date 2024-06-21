package com.Licenta.QuizSurprise.Repository;

import com.Licenta.QuizSurprise.Entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
}
