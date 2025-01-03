package com.example.finalprojectbond.Controller;

import com.example.finalprojectbond.Api.ApiResponse;
import com.example.finalprojectbond.InDTO.TaskInDTO;
import com.example.finalprojectbond.Model.Task;
import com.example.finalprojectbond.OutDTO.ExplorerOutDTO;
import com.example.finalprojectbond.OutDTO.TaskOutDTO;
import com.example.finalprojectbond.Service.TaskService;
import jakarta.validation.Valid;
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

    //12
    @GetMapping("/getAll/{authId}")
    public ResponseEntity getAllTasks(@PathVariable Integer authId) {
        return ResponseEntity.status(200).body(taskService.getAllTasks(authId));
    }

    //13
    @GetMapping("/getByExplorer/{explorerId}")
    public ResponseEntity getTasksByExplorer(@PathVariable Integer explorerId) {
        return ResponseEntity.status(200).body(taskService.getTasksByExplorer(explorerId));
    }

    //7
    @PostMapping("/add/{organizerId}/{explorerId}")
    public ResponseEntity createTask(@PathVariable Integer organizerId,
                                             @PathVariable Integer explorerId,
                                             @RequestBody @Valid Task task) {
        taskService.createTask(organizerId, explorerId, task);
        return ResponseEntity.status(200).body("Task created successfully");
    }

    @PutMapping("/update/{organizerId}/{explorerId}/{taskId}")
    public ResponseEntity updateTask(@PathVariable Integer organizerId,
                                             @PathVariable Integer explorerId,
                                             @PathVariable Integer taskId,
                                             @RequestBody @Valid TaskInDTO taskDTO) {
        taskService.updateTask(organizerId, explorerId, taskId, taskDTO);
        return ResponseEntity.status(200).body("Task updated successfully");
    }

    @DeleteMapping("/delete/{organizerId}/{explorerId}/{taskId}")
    public ResponseEntity deleteTask(@PathVariable Integer organizerId,
                                             @PathVariable Integer explorerId,
                                             @PathVariable Integer taskId) {
        taskService.deleteTask(organizerId, explorerId, taskId);
        return ResponseEntity.status(200).body("Task deleted successfully");
    }

    //8
    @PutMapping("/changeStatus/{organizerId}/{taskId}/{status}")
    public ResponseEntity changeTaskStatus(@PathVariable Integer organizerId,
                                                   @PathVariable Integer taskId,
                                                   @PathVariable String status) {
        taskService.changeTaskStatus(organizerId, taskId, status);
        return ResponseEntity.status(200).body("Task status updated successfully");
    }

    //9
    @GetMapping("/viewTaskProgress/{organizerId}")
    public ResponseEntity viewTaskProgressForAllExplorers(@PathVariable Integer organizerId) {
        return ResponseEntity.status(200).body(taskService.viewTaskProgressForAllExplorers(organizerId));
    }

    //10
    @GetMapping("/getIncompleteTasks/{explorerId}")
    public ResponseEntity getIncompleteTasksForExplorer(@PathVariable Integer explorerId) {
        return ResponseEntity.status(200).body(taskService.getIncompleteTasksForExplorer(explorerId));
    }

    //11
    @PutMapping("/markCompleted/{taskId}")
    public ResponseEntity<String> changeTaskStatusToCompleted(@PathVariable Integer taskId) {
        taskService.changeTaskStatusToCompleted(taskId);
        return ResponseEntity.status(200).body("Task marked as completed");
    }
}
