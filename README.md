# Zadanie - 2 - Team Task Management System

## Table of Contents

- [Introduction](#Introduction)
- [Project Description](#Project-Description)
- [Project Structure](#Project-Structure)
- [Technologies](#Technologies)
- [System Architecture](#System-Architecture)
- [Data Model](#Data-Model)
- [API Endpoints](#API-Endpoints)
  - [Users](#Users)
  - [Tasks](#Tasks)


## Introduction

The goal of this project is to develop a RESTful service in Java that enables the management of team tasks. The application allows for viewing, adding, editing, and deleting tasks and users, as well as assigning users to tasks. The API is designed to facilitate the creation of a simple frontend for managing and browsing tasks and users.

## Project Description

The team task management system provides:

- <b>User Management</b>: Searching with filtering, adding, and deleting users.
- <b>Task Management</b>: Searching with filtering, adding, editing, deleting, status updates, and assigning users to tasks.
- <b>Simplification</b>: The system does not require user authentication.

## Technologies

- <b>Programming Language</b>: Java 17+
- <b>Web Framework</b>: Spring Boot
- <b>ORM</b>: Hibernate (JPA)
- <b>Database</b>: H2 (embedded for simplicity)
- <b>Project Management</b>: Maven
- <b>Data Exchange Format</b>: JSON

## System Architecture

The application is based on a three-layer architecture:

- <b>Presentation Layer</b>: REST controllers handling HTTP requests.
- <b>Business Logic Layer</b>: Services containing the application's logic.
- <b>Data Access Layer</b>: Repositories interfacing with the database using JPA.

## Data Model

<b>User (`User`)</b>

|    Field     |     Type      |           Description            |
|     :---     |     :---      |               :---               |
|     `id`     |     Long      | Unique identifier for the user   |
| `firstName`  |     String    | First name                       |
| `lastName`   |     String    | Last name                        |
| `email`      |     String    | Email address                    |


<b>Task (`Task`)</b>

|    Field       |     Type          |           Description                              |
|     :---       |     :---          |               :---                                 |
|     `title`    |     String        | Title                                              |
| `description`  |     String        | Description                                        |
| `status`       |     TaskStatus    | Status (e.g., NEW, IN_PROGRESS, COMPLETED)         |
| `dueDate`      |     LocalDate     | Due date in format 'YYYY-MM-DD'                    |
| `users`        |     List<User>    | List of assigned users                             |


## API Endpoints

<b>Users</b>

1. Get a list of users with filtering
- <b>Endpoint</b>: `GET /api/users`
- <b>Optional Query Parameters</b>:
  - `firstName`: Filter users by first name.
  - `lastName`: Filter users by last name.
  - `email`: Filter users by email.
- <b>Description</b>: Retrieves a list of users that match the provided filtering criteria.
- <b>Response</b>:
- <b>Body</b>:
```
[
  {
    "id": 1,
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com"
  },
  ...
   ,
  {
    "id": 5,
    "firstName": "Jane",
    "lastName": "Smith",
    "email": "jane.smith@example.com"
  }
]
```
2. Add a new user

- <b>Endpoint</b>: `POST /api/users`
- <b>Request Body</b>:
```
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com"
}
```
- <b>Description</b>: Adds a new user to the system.
- <b>Body</b>:
```
{
  "id": 1,
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com"
}
```

3. Delete a user

- <b>Endpoint</b>: `DELETE /api/users/{id}`
- <b>Path Parameter</b>:
  - `id`: ID of the user to be deleted.
- <b>Description</b>: Deletes the user with the specified ID.

## Tasks
1. Get a list of tasks with filtering

- <b>Endpoint</b>: `GET /api/tasks`
- <b>Optional Query Parameters</b>:
  - `title`: Filter tasks by title.
  - `status`: Filter tasks by status (e.g., `NEW`, `IN_PROGRESS`, `COMPLETED`).
  - `dueDate`: Filter tasks by due date (format: `YYYY-MM-DD`).
  - `userId`: Filter tasks by assigned user ID.
- <b>Description</b>: Retrieves a list of tasks that match the provided filtering criteria.
- Response:
  - Body:
```
[
  {
    "id": 1,
    "title": "Create Documentation",
    "description": "Prepare the project's technical documentation",
    "status": "NEW",
    "dueDate": "2023-12-31",
    "users": [
      {
        "id": 1,
        "firstName": "John",
        "lastName": "Doe"
      }
    ]
  },
  {
    "id": 2,
    "title": "Review Code",
    "description": "Review the latest pull requests",
    "status": "IN_PROGRESS",
    "dueDate": "2023-11-15",
    "users": [
      {
        "id": 2,
        "firstName": "Jane",
        "lastName": "Smith"
      }
    ]
  }
]
```
2. Add a new task

- <b>Endpoint</b>: POST /api/tasks
- <b>Request Body</b>:
```
{
  "title": "Create Documentation",
  "description": "Prepare the project's technical documentation",
  "status": "NEW",
  "dueDate": "2023-12-31",
  "userIds": [1, 2]
}
```
- <b>Description</b>: Adds a new task and assigns users to it.
- Response:
  - Body:
```
{
  "id": 1,
  "title": "Create Documentation",
  "description": "Prepare the project's technical documentation",
  "status": "NEW",
  "dueDate": "2023-12-31",
  "users": [
    {
      "id": 1,
      "firstName": "John",
      "lastName": "Doe"
    },
    {
      "id": 2,
      "firstName": "Jane",
      "lastName": "Smith"
    }
  ]
}
```
3. Edit a task

- Endpoint: `PUT /api/tasks/{id}`
- <b>Path Parameter</b>:
  - `id`: ID of the task to be updated.
- <b>Request Body</b>: Same as adding a task.
- <b>Description</b>: Updates the task with the specified ID, including its title, description, status, due date, and assigned users.
- <b>Response</b>:
  - Body:
```
{
  "id": 1,
  "title": "Update Documentation",
  "description": "Revise the project's documentation",
  "status": "IN_PROGRESS",
  "dueDate": "2023-12-25",
  "users": [
    {
      "id": 2,
      "firstName": "Jane",
      "lastName": "Smith"
    }
  ]
}
```
4. Delete a task

- <b>Endpoint</b>: `DELETE /api/tasks/{id}`
- <b>Path Parameter</b>:
  - `id`: ID of the task to be deleted.
- <b>Description</b>: Deletes the task with the specified ID.

  
5. Change task status

- <b>Endpoint</b>: `PATCH /api/tasks/{id}/status`
- <b>Path Parameter</b>:
  - `id`: ID of the task to be updated.
Request Body:
```
{
  "status": "COMPLETED"
}
```
- <b>Description</b>: Changes the status of the task.
- <b>Response</b>:
  - <b>Body</b>:
```
{
  "id": 1,
  "title": "Update Documentation",
  "description": "Revise the project's documentation",
  "status": "COMPLETED",
  "dueDate": "2023-12-25",
  "users": []
}
```
6. Assign users to a task

- <b>Endpoint</b>: `PATCH /api/tasks/{id}/users`
- <b>Path Parameter</b>:
  - `id`: ID of the task to update users for.
- <b>Request Body</b>:
```
{
  "userIds": [1, 2]
}
```
- <b>Description</b>: Updates the list of users assigned to the task.
- <b>Response</b>:
  - <b>Body</b>:
```
{
  "id": 1,
  "title": "Create Documentation",
  "description": "Prepare the project's technical documentation",
  "status": "NEW",
  "dueDate": "2023-12-31",
  "users": [
    {
      "id": 1,
      "firstName": "John",
      "lastName": "Doe"
    },
    {
      "id": 2,
      "firstName": "Jane",
      "lastName": "Smith"
    }
  ]
}
```
***

This documentation covers all the functionality provided by the Team Task Management System API, ensuring clarity on how each endpoint is used, the expected input, and the output. It should be suitable for use in your README file, helping other developers or users understand how to interact with your API.
		
