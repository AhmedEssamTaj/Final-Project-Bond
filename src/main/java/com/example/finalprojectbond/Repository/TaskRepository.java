package com.example.finalprojectbond.Repository;

import com.example.finalprojectbond.Model.Explorer;
import com.example.finalprojectbond.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    Task findTaskById(Integer Id);

    List<Task> findTasksByExplorer(Explorer explorer);

    Task findTaskByIdAndExplorer(Integer taskId, Explorer explorer);
}
