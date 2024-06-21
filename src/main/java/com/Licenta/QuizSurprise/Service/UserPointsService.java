package com.Licenta.QuizSurprise.Service;

import com.Licenta.QuizSurprise.Entity.UserPoints;
import com.Licenta.QuizSurprise.Repository.UserPointsRepository;
import com.Licenta.QuizSurprise.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserPointsService {

    @Autowired
    private UserPointsRepository userPointsRepository;

    @Autowired
    private UserRepository userRepository;

    public UserPoints getUserPointsByUserId(Integer userId) {
        return userPointsRepository.findUserPointsByUserId(userId);
    }

    public UserPoints updateUserPoints(UserPoints userPoints) {
        return userPointsRepository.save(userPoints);
    }

    public void restartUserPoints(Integer userId) {
        UserPoints userPoints = userPointsRepository.findUserPointsByUserId(userId);
        if (userPoints != null) {
            userPoints.setTotalPoints(0);
            userPointsRepository.delete(userPoints);
        }
    }
}
