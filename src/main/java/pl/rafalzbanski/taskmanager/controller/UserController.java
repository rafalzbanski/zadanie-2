package pl.rafalzbanski.taskmanager.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.rafalzbanski.taskmanager.model.User;
import pl.rafalzbanski.taskmanager.service.UserService;

import java.util.List;

/**
 * REST controller for managing users.
 */
@RestController
@RequestMapping("/api/users")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Retrieves a list of users with optional filtering.
     */
    @GetMapping
    public List<User> getAllUsers(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email) {
        return userService.getAllUsers(firstName, lastName, email);
    }

    /**
     * Creates a new user.
     */
    @PostMapping
    public User createUser(@RequestBody @Validated User user) {
        return userService.createUser(user);
    }

    /**
     * Deletes a user by ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
