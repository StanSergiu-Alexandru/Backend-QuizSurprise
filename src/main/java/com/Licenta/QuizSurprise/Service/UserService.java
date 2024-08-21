package com.Licenta.QuizSurprise.Service;

import com.Licenta.QuizSurprise.Entity.User;
import com.Licenta.QuizSurprise.Repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PersistenceContext
    private EntityManager em;

    public List<User> getAllUsers() {return userRepository.findAll();}

    public User getUserByUsername(String username) {return userRepository.findByUsername(username);}

    public User getUserById(int id) {return userRepository.findById(id).orElse(null);}

    public void createUser(User user) {userRepository.save(user);}

    public boolean deleteUser(Integer id) {
        Optional<User> existingUser = userRepository.findById(id);

        if (existingUser.isPresent()) {
            userRepository.delete(existingUser.get());
            return true;
        } else {
            return false;
        }
    }

    public User updateUser(User user) {return userRepository.save(user);}

    public Optional<User> findUserById(int id) {return userRepository.findById(id);}

    public User registerUser(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new RuntimeException("Username already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
