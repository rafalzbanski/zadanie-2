package pl.rafalzbanski.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.rafalzbanski.taskmanager.model.User;

import java.util.List;

/**
 * Repository interface for User entities.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find users by first name (case-insensitive, partial match).
     */
    List<User> findByFirstNameIgnoreCaseContaining(String firstName);

    /**
     * Find users by last name (case-insensitive, partial match).
     */
    List<User> findByLastNameIgnoreCaseContaining(String lastName);

    /**
     * Find users by email (case-insensitive, exact match).
     */
    List<User> findByEmailIgnoreCase(String email);

    /**
     * Find users by first name and last name.
     */
    List<User> findByFirstNameIgnoreCaseContainingAndLastNameIgnoreCaseContaining(String firstName, String lastName);

    /**
     * Find users by first name, last name, and email.
     */
    List<User> findByFirstNameIgnoreCaseContainingAndLastNameIgnoreCaseContainingAndEmailIgnoreCaseContaining(
            String firstName, String lastName, String email);
}
