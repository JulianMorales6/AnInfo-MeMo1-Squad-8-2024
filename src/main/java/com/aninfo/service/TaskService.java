package com.aninfo.service;

import com.aninfo.model.Task;
import com.aninfo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(Task Task) {
        return taskRepository.save(Task);
    }

    public Collection<Task> getTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    public void save(Task Task) {
        taskRepository.save(Task);
    }

    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }

    public void assignEmployee(Long task_id, Long assigned_Employee) {
        Task task = taskRepository.findTaskById(task_id);
        task.assignEmployee(assigned_Employee);
        taskRepository.save(task);
    }
}