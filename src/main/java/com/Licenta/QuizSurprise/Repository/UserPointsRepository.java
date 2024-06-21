package com.Licenta.QuizSurprise.Repository;

import com.Licenta.QuizSurprise.Entity.UserPoints;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPointsRepository extends JpaRepository<UserPoints, Integer> {
    UserPoints findUserPointsByUserId(Integer userId);
}
