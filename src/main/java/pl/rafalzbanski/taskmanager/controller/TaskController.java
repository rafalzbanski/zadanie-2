package pl.rafalzbanski.taskmanager.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.rafalzbanski.taskmanager.model.Task;
import pl.rafalzbanski.taskmanager.model.TaskStatus;
import pl.rafalzbanski.taskmanager.service.TaskService;


import java.time.LocalDate;
import java.util.List;

/**
 * REST controller for managing tasks.
 */
@RestController
@RequestMapping("/api/tasks")
@Validated
public class TaskController {

    @Autowired
    private TaskService taskService;

    /**
     * Retrieves a list of tasks with optional filtering.
     */
    @GetMapping
    public List<Task> getAllTasks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) TaskStatus status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dueDate,
            @RequestParam(required = false) Long userId) {
        return taskService.getAllTasks(title, status, dueDate, userId);
    }

    /**
     * Creates a new task with assigned users.
     */
    @PostMapping
    public Task createTask(@RequestBody @Validated  TaskRequest taskRequest) {
        return taskService.createTask(taskRequest.toTask(), taskRequest.getUserIds());
    }

    /**
     * Updates an existing task by ID.
     */
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody @Validated TaskRequest taskRequest) {
        return taskService.updateTask(id, taskRequest.toTask(), taskRequest.getUserIds());
    }

    /**
     * Deletes a task by ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Updates the status of a task.
     */
    @PatchMapping("/{id}/status")
    public Task updateTaskStatus(@PathVariable Long id, @RequestBody TaskStatusRequest statusRequest) {
        return taskService.updateTaskStatus(id, statusRequest.getStatus());
    }

    /**
     * Updates the users assigned to a task.
     */
    @PatchMapping("/{id}/users")
    public Task updateTaskUsers(@PathVariable Long id, @RequestBody UserIdsRequest userIdsRequest) {
        return taskService.updateTaskUsers(id, userIdsRequest.getUserIds());
    }
}
