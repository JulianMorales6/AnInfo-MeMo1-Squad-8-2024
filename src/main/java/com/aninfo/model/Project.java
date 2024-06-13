package com.aninfo.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    String title;
    String description;

    @Enumerated(EnumType.STRING)
    private ProjectState state;

    private Long assignedLeader;

    private LocalDateTime startDateTime;
    private LocalDateTime finishDateTime;
    
    public Project(){
    }

    public Project(String title, String description ) {
        this.title = title;
        this.description = description;
        this.state = ProjectState.OPEN;
        this.startDateTime = LocalDateTime.now();
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Long getAssignedLeader() {
        return assignedLeader;
    }

    public ProjectState getState() {
        return state;
    }

    public LocalDateTime getStartDate() {
        return startDateTime;
    }

    public LocalDateTime getFinishDate() {
        return finishDateTime;
    }

    public void assignEmployee(Long assignedLeader) {
        this.assignedLeader = assignedLeader;
        this.state = ProjectState.PROGRESS;
    }


    public void close() {
        this.state = ProjectState.CLOSED;
    }


    public void block() {
        this.state = ProjectState.BLOCKED;
    }

    public void finish() {
        this.state = ProjectState.FINISHED;
        this.finishDateTime = LocalDateTime.now();
    }


}
