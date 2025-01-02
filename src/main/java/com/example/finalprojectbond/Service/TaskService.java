package com.example.finalprojectbond.Service;

import com.example.finalprojectbond.Api.ApiException;
import com.example.finalprojectbond.InDTO.TaskInDTO;
import com.example.finalprojectbond.Model.*;
import com.example.finalprojectbond.OutDTO.ExplorerOutDTO;
import com.example.finalprojectbond.OutDTO.TaskOutDTO;
import com.example.finalprojectbond.Repository.AuthRepository;
import com.example.finalprojectbond.Repository.ExplorerRepository;
import com.example.finalprojectbond.Repository.OrganizerRepository;
import com.example.finalprojectbond.Repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final AuthRepository authRepository;
    private final ExplorerRepository explorerRepository;
    private final OrganizerRepository organizerRepository;

    public List<Task> getAllTasks(Integer authId) {
        MyUser auth = authRepository.findMyUserById(authId);
        if (auth == null) {
            throw new ApiException("Admin or Employee with ID: " + authId + " was not found");
        }

        if (auth.getRole().equals("ADMIN") || auth.getRole().equals("EMPLOYEE")) {
            return taskRepository.findAll();
        } else {
            throw new ApiException("You don't have the permission to access this endpoint");
        }
    }

    public List<TaskOutDTO> getTasksByExplorer(Integer explorerId) {
        Explorer explorer = explorerRepository.findExplorerById(explorerId);
        if (explorer == null) {
            throw new ApiException("Explorer was not found");
        }

        List<Task> tasks = taskRepository.findTasksByExplorer(explorer);
        if (tasks.isEmpty()) {
            throw new ApiException("This explorer has no tasks yet");
        }

        List<TaskOutDTO> taskDTOS = new ArrayList<>();
        for (Task task : tasks) {
            taskDTOS.add(new TaskOutDTO(task.getTitle(), task.getDescription(), task.getStatus()));
        }

        return taskDTOS;
    }

    //1
    public void createTask(Integer organizerId, Integer explorerId, Task task) {
        Organizer organizer = organizerRepository.findOrganizerById(organizerId);
        if (organizer == null || !organizer.getMyUser().getRole().equals("ORGANIZER")) {
            throw new ApiException("You don't have permission to create tasks");
        }

        Explorer explorer = explorerRepository.findExplorerById(explorerId);
        if (explorer == null) {
            throw new ApiException("Explorer was not found");
        }

        boolean explorerInExperience = false;
        for (Experience exp : explorer.getExperiences()) {
            if (exp.getId().equals(task.getExplorer().getId())) {
                explorerInExperience = true;
                break;
            }
        }


        Task taskToCreate = new Task();
        taskToCreate.setTitle(task.getTitle());
        taskToCreate.setDescription(task.getDescription());
        taskToCreate.setStatus(task.getStatus());
        taskToCreate.setExplorer(explorer);

        taskRepository.save(taskToCreate);

        explorer.getTasks().add(taskToCreate);

        explorerRepository.save(explorer);
    }


    public void updateTask(Integer organizerId, Integer explorerId, Integer taskId, TaskInDTO taskDTO) {

        Organizer organizer = organizerRepository.findOrganizerById(organizerId);
        if (organizer == null || !organizer.getMyUser().getRole().equals("ORGANIZER")) {
            throw new ApiException("You don't have permission to update tasks");
        }

        Explorer explorer = explorerRepository.findExplorerById(explorerId);
        if (explorer == null) {
            throw new ApiException("Explorer was not found");
        }
        Task task = taskRepository.findTaskByIdAndExplorer(taskId, explorer);
        if (task == null) {
            throw new ApiException("Task was not found");
        }

        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setStatus(taskDTO.getStatus());

        taskRepository.save(task);
    }


    public void deleteTask(Integer organizerId, Integer explorerId, Integer taskId) {
        Organizer organizer = organizerRepository.findOrganizerById(organizerId);
        if (organizer == null || !organizer.getMyUser().getRole().equals("ORGANIZER")) {
            throw new ApiException("You don't have permission to delete tasks");
        }

        Explorer explorer = explorerRepository.findExplorerById(explorerId);
        if (explorer == null) {
            throw new ApiException("Explorer was not found");
        }

        Task task = taskRepository.findTaskByIdAndExplorer(taskId, explorer);
        if (task == null) {
            throw new ApiException("Task was not found");
        }

        taskRepository.delete(task);
    }


    //2
    public void changeTaskStatus(Integer organizerId, Integer taskId, String status) {
        Organizer organizer = organizerRepository.findOrganizerById(organizerId);
        if (organizer == null || !organizer.getMyUser().getRole().equals("ORGANIZER")) {
            throw new ApiException("You don't have permission to change task status");
        }

        Task task = taskRepository.findTaskById(taskId);
        if (task == null) {
            throw new ApiException("Task was not found");
        }

        if (!status.equals("Complete") && !status.equals("In-Complete")) {
            throw new ApiException("Invalid status. Allowed values are 'Complete' or 'In-Complete'");
        }


        task.setStatus(status);
        taskRepository.save(task);
    }

    //3
    public List<ExplorerOutDTO> viewTaskProgressForAllExplorers(Integer organizerId) {
        Organizer organizer = organizerRepository.findOrganizerById(organizerId);
        if (organizer == null || !organizer.getMyUser().getRole().equals("ORGANIZER")) {
            throw new ApiException("You don't have permission to view task progress");
        }

        List<Explorer> explorers = explorerRepository.findAll();
        if (explorers == null) {
            throw new ApiException("No explorers found");
        }

        List<ExplorerOutDTO> progressList = new ArrayList<>();
        for (Explorer explorer : explorers) {
            List<Task> tasks = taskRepository.findTasksByExplorer(explorer);
            int totalTasks = tasks.size();
            int completedTasks = 0;

            for (Task task : tasks) {
                if ("Complete".equals(task.getStatus())) {
                    completedTasks++;
                }
            }

            ExplorerOutDTO explorerOutDTO = new ExplorerOutDTO(explorer.getMyUser().getUsername(), explorer.getMyUser().getAge(), explorer.getMyUser().getEmail(),
                    explorer.getMyUser().getGender(), explorer.getMyUser().getPhoneNumber());
            progressList.add(explorerOutDTO);
        }

        return progressList;
    }

    //4
    public List<TaskOutDTO> viewAllTasksForExplorer(Integer organizerId, Integer explorerId) {
        Organizer organizer = organizerRepository.findOrganizerById(organizerId);
        if (organizer == null || !organizer.getMyUser().getRole().equals("ORGANIZER")) {
            throw new ApiException("You don't have permission to view tasks");
        }

        Explorer explorer = explorerRepository.findExplorerById(explorerId);
        if (explorer == null) {
            throw new ApiException("Explorer was not found");
        }

        List<Task> tasks = taskRepository.findTasksByExplorer(explorer);
        if (tasks.isEmpty()) {
            throw new ApiException("This Explorer has no tasks");
        }

        List<TaskOutDTO> taskDTOs = new ArrayList<>();
        for (Task task : tasks) {

            TaskOutDTO taskDTO = new TaskOutDTO(task.getTitle(), task.getDescription(), task.getStatus());
            taskDTOs.add(taskDTO);
        }
        return taskDTOs;
    }


}
