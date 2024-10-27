package pl.rafalzbanski.taskmanager.controller;


import jakarta.validation.constraints.NotNull;
import pl.rafalzbanski.taskmanager.model.TaskStatus;

/**
 * Request body for updating a task's status.
 */
public class TaskStatusRequest {

    @NotNull(message = "Status is required")
    private TaskStatus status;

    // Getter and setter

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}
