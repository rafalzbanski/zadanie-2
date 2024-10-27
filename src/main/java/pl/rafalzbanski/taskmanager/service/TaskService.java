package pl.rafalzbanski.taskmanager.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.rafalzbanski.taskmanager.exception.ResourceNotFoundException;
import pl.rafalzbanski.taskmanager.model.Task;
import pl.rafalzbanski.taskmanager.model.TaskStatus;
import pl.rafalzbanski.taskmanager.model.User;
import pl.rafalzbanski.taskmanager.repository.TaskRepository;
import pl.rafalzbanski.taskmanager.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * Service class for managing tasks.
 */
@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Retrieves all tasks with optional filtering.
     */
    public List<Task> getAllTasks(String title, TaskStatus status, LocalDate dueDate, Long userId) {
        if (title != null && status != null && userId != null) {
            return taskRepository.findByTitleIgnoreCaseContainingAndStatusAndUsers_Id(title, status, userId);
        } else if (title != null && status != null) {
            return taskRepository.findByTitleIgnoreCaseContainingAndStatus(title, status);
        } else if (status != null && userId != null) {
            return taskRepository.findByStatusAndUsers_Id(status, userId);
        } else if (title != null && userId != null) {
            return taskRepository.findByTitleIgnoreCaseContainingAndUsers_Id(title, userId);
        } else if (title != null) {
            return taskRepository.findByTitleIgnoreCaseContaining(title);
        } else if (status != null) {
            return taskRepository.findByStatus(status);
        } else if (dueDate != null) {
            return taskRepository.findByDueDate(dueDate);
        } else if (userId != null) {
            return taskRepository.findByUsers_Id(userId);
        } else {
            return taskRepository.findAll();
        }
    }

    /**
     * Creates a new task with assigned users.
     */
    public Task createTask(Task task, List<Long> userIds) {
        List<User> users = userRepository.findAllById(userIds);
        task.setUsers(users);
        return taskRepository.save(task);
    }

    /**
     * Updates an existing task by ID.
     */
    public Task updateTask(Long id, Task taskDetails, List<Long> userIds) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setStatus(taskDetails.getStatus());
        task.setDueDate(taskDetails.getDueDate());

        List<User> users = userRepository.findAllById(userIds);
        task.setUsers(users);

        return taskRepository.save(task);
    }

    /**
     * Deletes a task by ID.
     */
    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));
        taskRepository.delete(task);
    }

    /**
     * Updates the status of a task.
     */
    public Task updateTaskStatus(Long id, TaskStatus status) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));
        task.setStatus(status);
        return taskRepository.save(task);
    }

    /**
     * Updates the list of users assigned to a task.
     */
    public Task updateTaskUsers(Long id, List<Long> userIds) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));
        List<User> users = userRepository.findAllById(userIds);
        task.setUsers(users);
        return taskRepository.save(task);
    }
}
