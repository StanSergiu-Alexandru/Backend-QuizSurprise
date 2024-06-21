package com.Licenta.QuizSurprise.Controller;

import com.Licenta.QuizSurprise.Entity.User;
import com.Licenta.QuizSurprise.Repository.UserRepository;
import com.Licenta.QuizSurprise.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/quizsurprise")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/getUsers")
    public List<User> getAllUsers() {return userService.getAllUsers();}

    @PostMapping("/createUser")
    public void createUser(@RequestBody User user) {userService.createUser(user);}

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable int id) {
        Optional<User> oldUserOptional = userRepository.findById(id);

        if (oldUserOptional.isPresent()) {
            User oldUser = oldUserOptional.get();

            if (user.getFirstName() != null) {
                oldUser.setFirstName(user.getFirstName());
            }
            if (user.getLastName() != null) {
                oldUser.setLastName(user.getLastName());
            }
            if (user.getPassword() != null) {
                oldUser.setPassword(user.getPassword());
            }
            if (user.getUserGroup() != null) {
                oldUser.setUserGroup(user.getUserGroup());
            }

            User updatedUser = userService.updateUser(oldUser);
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        boolean isDeleted = userService.deleteUser(id);

        if (isDeleted)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.notFound().build();
    }

}
