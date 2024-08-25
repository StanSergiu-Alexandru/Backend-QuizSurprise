package com.Licenta.QuizSurprise.Repository;

import com.Licenta.QuizSurprise.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    List<User> findAllByOrderByUserPointsTotalPointsDesc();
}
