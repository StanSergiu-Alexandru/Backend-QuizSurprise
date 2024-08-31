package com.Licenta.QuizSurprise.Service;

import com.Licenta.QuizSurprise.Config.JWTUtil;
import com.Licenta.QuizSurprise.Entity.User;
import com.Licenta.QuizSurprise.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTUtil jwtUtil;

    public String authenticate(String username, String password) {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new RuntimeException("Nume utilizator sau parola incorecta");
        }

            LocalDate currentDate = LocalDate.now();

            LocalDate lastAccessed = user.getLastAccessed();


        if (lastAccessed != null && currentDate.equals(lastAccessed)) {
            throw new RuntimeException("Ai raspuns deja la intrebare astazi. Revino maine!");
        }

        if (passwordEncoder.matches(password, user.getPassword())) {
            user.setLastAccessed(currentDate);
            if(Objects.equals(user.getUsername(), "Test")){
                user.setLastAccessed(null);
            }
            userRepository.save(user);

            return jwtUtil.generateToken(username);
        }

        throw new RuntimeException("Nume utilizator sau parola incorecta");
    }
}