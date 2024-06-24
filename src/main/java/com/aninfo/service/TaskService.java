package com.aninfo.service;


import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aninfo.model.AssociationDTO;
import com.aninfo.model.Task;
import com.aninfo.model.TaskPriority;
import com.aninfo.model.TaskState;
import com.aninfo.repository.TaskRepository;
import com.aninfo.service.TicketSeverityService;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TicketSeverityService ticketSeverityService;

    public Task createTask(Task Task) {
        if(Task.getStartDate() == null) {
            Task.setStartDateTime(LocalDateTime.now());
        }
        if(Task.getPriority() == null) {
            Task.setPriority(TaskPriority.LOW);
        }
        if(Task.getState() == null) {
            Task.setState(TaskState.OPEN);
        }
        return taskRepository.save(Task);
    }

    public Collection<Task> getTasks() {
        return taskRepository.findAll();
    }
    
    public Collection<Task> getTasksFromProject(Long project_id) {
        return taskRepository.findByProjectId(project_id);
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

    public void changeState(Long task_id, String state) {
        Task task = taskRepository.findTaskById(task_id);

        if (state.equals(TaskState.BLOCKED.toString())) {
            task.setState(TaskState.BLOCKED);
        }
        else if (state.equals(TaskState.CLOSED.toString())) {
            task.setState(TaskState.CLOSED);
        }
        else if (state.equals(TaskState.FINISHED.toString())) {
            task.setState(TaskState.FINISHED);
        }
        taskRepository.save(task);
    }

    public void changePriority(Long task_id, String priority) {
        Task task = taskRepository.findTaskById(task_id);

        if (priority.equals(TaskPriority.HIGH.toString()))
            task.setPriority(TaskPriority.HIGH);
        else if (priority.equals(TaskPriority.MEDIUM.toString()))
            task.setPriority(TaskPriority.MEDIUM);
        else if (priority.equals(TaskPriority.LOW.toString()))
            task.setPriority(TaskPriority.LOW);

        taskRepository.save(task);
    }

    public void associateTicketToTasks(Long ticket_id, List<Long> task_ids) {
        this.updateTickets(ticket_id, task_ids, task ->{
             task.associateTicket(ticket_id);
             task.setDaysToComplete(ticketSeverityService.getSeverity(task.getFirstTicketId()) , task.getFirstTicketDate());
            });
    }

    public void disassociateTicketToTasks(Long ticket_id, List<Long> task_ids) {
        this.updateTickets(ticket_id, task_ids, task -> {
            task.disassociateTicket(ticket_id);
            task.setDaysToComplete(ticketSeverityService.getSeverity(task.getFirstTicketId()) , task.getFirstTicketDate());
            });
    }

    public void updateTickets(Long ticket_id, List<Long> task_ids, Consumer<Task> taskOperation) {
        task_ids.forEach(task_id -> {
            Optional<Task> optionalTask = taskRepository.findById(task_id);
            if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            taskOperation.accept(task);
            taskRepository.save(task);
        } else {
            //por ahora nada
        }
        });
    }

    public void updateTicketAssociation(AssociationDTO associationDTO) {

        if (!associationDTO.getUpList().isEmpty()) {
            associateTicketToTasks(associationDTO.getTicketId(), associationDTO.getUpList());
        }
        if (!associationDTO.getDownList().isEmpty()) {
            disassociateTicketToTasks(associationDTO.getTicketId(), associationDTO.getDownList());
        }
    }

    public List<Task> getTasksByIds(List<Long> taskIds) {
        return taskRepository.findAllById(taskIds);
    }

}
