package com.Licenta.QuizSurprise.Controller;

import com.Licenta.QuizSurprise.Entity.User;
import com.Licenta.QuizSurprise.Entity.UserPoints;
import com.Licenta.QuizSurprise.Repository.UserRepository;
import com.Licenta.QuizSurprise.Service.UserPointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/quizsurprise/userpoints")
public class UserPointsController {

    @Autowired
    private UserPointsService userPointsService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/get/{userId}")
    public ResponseEntity<UserPoints> getUserPointsByUserId(@PathVariable Integer userId) {

        Optional<User> existsUser = userRepository.findById(userId);

        if (existsUser.isPresent()) {
            UserPoints userPoints = userPointsService.getUserPointsByUserId(userId);
            return ResponseEntity.ok(userPoints);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/update/{userId}")
    public ResponseEntity<UserPoints> updateUserPoints(@RequestBody UserPoints userPoints, @PathVariable Integer userId) {
        Optional<User> existsUser = userRepository.findById(userId);

        if (existsUser.isPresent()) {
            User user = existsUser.get();
            UserPoints existingUserPoints = user.getUserPoints();

            if (existingUserPoints == null) {
                existingUserPoints = new UserPoints();
                existingUserPoints.setUser(user);
                existingUserPoints.setTotalPoints(0);
            }

            existingUserPoints.setTotalPoints(existingUserPoints.getTotalPoints() + userPoints.getTotalPoints());

            UserPoints updatedUserPoints = userPointsService.updateUserPoints(existingUserPoints);
            return ResponseEntity.ok(updatedUserPoints);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/restart/{userId}")
    public ResponseEntity<UserPoints> restartUserPoints(@PathVariable Integer userId) {
        userPointsService.restartUserPoints(userId);
        return ResponseEntity.noContent().build();
    }
}
