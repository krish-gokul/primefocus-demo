package com.example.demo;

import com.example.demo.User;
import com.example.demo.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public User saveUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("/{username}")
    public User updateUser(@PathVariable String username, @RequestParam String newName) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            user.setName(newName);
            return userRepository.save(user);
        }
        return null; // Handle not found case
    }

    @GetMapping
    public List<User> getUsersSortedByDateOfBirth() {
        Sort sortByDateOfBirth = Sort.by(Sort.Direction.ASC, "dateOfBirth");
        return userRepository.findAll(sortByDateOfBirth);
    }
}