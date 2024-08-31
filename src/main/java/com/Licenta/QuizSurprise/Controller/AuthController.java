package com.Licenta.QuizSurprise.Controller;

import com.Licenta.QuizSurprise.DTO.LoginDTO;
import com.Licenta.QuizSurprise.DTO.UserDTO;
import com.Licenta.QuizSurprise.Entity.User;
import com.Licenta.QuizSurprise.Entity.UserPoints;
import com.Licenta.QuizSurprise.Service.AuthService;
import com.Licenta.QuizSurprise.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody UserDTO userDTO) throws Exception {
        User existingUser = userService.findByUsername(userDTO.getUsername());
        if (existingUser != null) {
            throw new Exception("User Exists.");
        }
        User user = new User();
        UserPoints userPoints = new UserPoints();
        userPoints.setUser(user);
        userPoints.setTotalPoints(0);
        userPoints.setId(user.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setUserGroup(userDTO.getUserGroup());
        user.setUserPoints(userPoints);
        user.setLastAccessed(null);
        User registeredUser = userService.registerUser(user);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<HashMap<String,Object>> authenticateUser(@Valid @RequestBody LoginDTO loginRequest) {
        try {
            String jwt = authenticationService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
            User user = userService.findByUsername(loginRequest.getUsername());
            HashMap<String, Object> response = new HashMap<>();
            response.put("token", jwt);
            response.put("first_name", user.getFirstName());
            response.put("last_name", user.getLastName());
            response.put("user_id", user.getId());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            HashMap<String, Object> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.ok(error);
        }
    }
}
