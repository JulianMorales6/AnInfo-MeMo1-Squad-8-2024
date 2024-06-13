package com.aninfo.model;

import java.time.Duration;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private TaskState state;

    private Long assignedEmployee;

    private LocalDateTime startDateTime;
    private LocalDateTime finishDateTime;
    private Duration maximumResolutionTime;

    public Task(){
    }

    public Task(String title, String description ) {
        this.title = title;
        this.description = description;
        this.state = TaskState.OPEN;
        this.startDateTime = LocalDateTime.now();
    }

    public Task(String title, String description, Duration maximumResolutionTime ) {
        this.title = title;
        this.description = description;
        this.state = TaskState.OPEN;
        this.startDateTime = LocalDateTime.now();
        this.maximumResolutionTime = maximumResolutionTime;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Long getAssignedEmployee() {
        return assignedEmployee;
    }

    public TaskState getState() {
        return state;
    }

    public LocalDateTime getStartDate() {
        return startDateTime;
    }

    public LocalDateTime getFinishDate() {
        return finishDateTime;
    }

    public Duration getMaximumResolutionTime() {
        return maximumResolutionTime;
    }

    public void assignEmployee(Long assignedEmployee) {
        this.assignedEmployee = assignedEmployee;
        this.state = TaskState.PROGRESS;
    }


    public void close() {
        this.state = TaskState.CLOSED;
    }


    public void block() {
        this.state = TaskState.BLOCKED;
    }

    public void finish() {
        this.state = TaskState.FINISHED;
        this.finishDateTime = LocalDateTime.now();
    }

}
