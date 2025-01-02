package com.example.finalprojectbond.Controller;

import com.example.finalprojectbond.Api.ApiResponse;
import com.example.finalprojectbond.InDTO.TaskInDTO;
import com.example.finalprojectbond.Model.Task;
import com.example.finalprojectbond.OutDTO.ExplorerOutDTO;
import com.example.finalprojectbond.OutDTO.TaskOutDTO;
import com.example.finalprojectbond.Service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/task")
@RequiredArgsConstructor
public class TaskController {


    private final TaskService taskService;


    @GetMapping("/all/{authId}")
    public ResponseEntity getAllTasks(@PathVariable Integer authId) {
        List<Task> tasks = taskService.getAllTasks(authId);
        return ResponseEntity.status(HttpStatus.OK).body( "Tasks retrieved successfully");
    }

    @GetMapping("/explorer/{explorerId}")
    public ResponseEntity getTasksByExplorer(@PathVariable Integer explorerId) {
        List<TaskOutDTO> tasks = taskService.getTasksByExplorer(explorerId);
        return ResponseEntity.status(HttpStatus.OK).body( "Tasks retrieved successfully");
    }

    @PostMapping("/create/{organizerId}/{explorerId}")
    public ResponseEntity createTask(@PathVariable Integer organizerId, @PathVariable Integer explorerId, @RequestBody @Validated Task task) {

        taskService.createTask(organizerId, explorerId, task);
        return ResponseEntity.status(HttpStatus.OK).body("Task created successfully");
    }

    @PutMapping("/update/{organizerId}/{explorerId}/{taskId}")
    public ResponseEntity updateTask(@PathVariable Integer organizerId, @PathVariable Integer explorerId, @PathVariable Integer taskId, @RequestBody @Validated TaskInDTO taskDTO) {
        taskService.updateTask(organizerId, explorerId, taskId, taskDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Task updated successfully");
    }

    @DeleteMapping("/delete/{organizerId}/{explorerId}/{taskId}")
    public ResponseEntity deleteTask(@PathVariable Integer organizerId, @PathVariable Integer explorerId, @PathVariable Integer taskId) {
        taskService.deleteTask(organizerId, explorerId, taskId);
        return ResponseEntity.status(HttpStatus.OK).body("Task deleted successfully");
    }

    @PutMapping("/change-status/{organizerId}/{taskId}")
    public ResponseEntity changeTaskStatus(@PathVariable Integer organizerId, @PathVariable Integer taskId, @RequestParam String status) {
        taskService.changeTaskStatus(organizerId, taskId, status);
        return ResponseEntity.status(HttpStatus.OK).body("Task status changed successfully");
    }

    @GetMapping("/progress/{organizerId}")
    public ResponseEntity viewTaskProgressForAllExplorers(@PathVariable Integer organizerId) {
        List<ExplorerOutDTO> progressList = taskService.viewTaskProgressForAllExplorers(organizerId);
        return ResponseEntity.status(HttpStatus.OK).body("Task progress retrieved successfully");
    }

    @GetMapping("/explorer-tasks/{organizerId}/{explorerId}")
    public ResponseEntity viewAllTasksForExplorer(@PathVariable Integer organizerId, @PathVariable Integer explorerId) {
        List<TaskOutDTO> tasks = taskService.viewAllTasksForExplorer(organizerId, explorerId);
        return ResponseEntity.status(HttpStatus.OK).body("Tasks retrieved successfully");
    }
}
