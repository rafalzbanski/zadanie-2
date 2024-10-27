package pl.rafalzbanski.taskmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.rafalzbanski.taskmanager.exception.ResourceNotFoundException;
import pl.rafalzbanski.taskmanager.model.User;
import pl.rafalzbanski.taskmanager.repository.UserRepository;

import java.util.List;

/**
 * Service class for managing users.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Retrieves all users with optional filtering.
     */
    public List<User> getAllUsers(String firstName, String lastName, String email) {
        if (firstName != null && lastName != null && email != null) {
            return userRepository.findByFirstNameIgnoreCaseContainingAndLastNameIgnoreCaseContainingAndEmailIgnoreCaseContaining(
                    firstName, lastName, email);
        } else if (firstName != null && lastName != null) {
            return userRepository.findByFirstNameIgnoreCaseContainingAndLastNameIgnoreCaseContaining(firstName, lastName);
        } else if (firstName != null) {
            return userRepository.findByFirstNameIgnoreCaseContaining(firstName);
        } else if (lastName != null) {
            return userRepository.findByLastNameIgnoreCaseContaining(lastName);
        } else if (email != null) {
            return userRepository.findByEmailIgnoreCase(email);
        } else {
            return userRepository.findAll();
        }
    }

    /**
     * Creates a new user.
     */
    public User createUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Deletes a user by ID.
     */
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userRepository.delete(user);
    }
}
