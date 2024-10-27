package pl.rafalzbanski.taskmanager.controller;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

/**
 * Request body for updating the users assigned to a task.
 */
public class UserIdsRequest {

    @NotEmpty(message = "User list cannot be empty")
    private List<Long> userIds;

    // Getter and setter

    public List<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Long> userIds) {
        this.userIds = userIds;
    }
}
