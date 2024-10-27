package pl.rafalzbanski.taskmanager.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.rafalzbanski.taskmanager.model.Task;
import pl.rafalzbanski.taskmanager.model.TaskStatus;

import java.time.LocalDate;
import java.util.List;

/**
 * Repository interface for Task entities.
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    /**
     * Find tasks by title (case-insensitive, partial match).
     */
    List<Task> findByTitleIgnoreCaseContaining(String title);

    /**
     * Find tasks by status.
     */
    List<Task> findByStatus(TaskStatus status);

    /**
     * Find tasks by due date.
     */
    List<Task> findByDueDate(LocalDate dueDate);

    /**
     * Find tasks with due date before the specified date.
     */
    List<Task> findByDueDateBefore(LocalDate dueDate);

    /**
     * Find tasks with due date after the specified date.
     */
    List<Task> findByDueDateAfter(LocalDate dueDate);

    /**
     * Find tasks assigned to a specific user.
     */
    List<Task> findByUsers_Id(Long userId);

    /**
     * Find tasks by status and assigned user.
     */
    List<Task> findByStatusAndUsers_Id(TaskStatus status, Long userId);

    /**
     * Find tasks by title and status.
     */
    List<Task> findByTitleIgnoreCaseContainingAndStatus(String title, TaskStatus status);

    /**
     * Find tasks by title, status, and assigned user.
     */
    List<Task> findByTitleIgnoreCaseContainingAndStatusAndUsers_Id(String title, TaskStatus status, Long userId);

    /**
     * Find tasks by title and assigned user.
     */
    List<Task> findByTitleIgnoreCaseContainingAndUsers_Id(String title, Long userId);
}
