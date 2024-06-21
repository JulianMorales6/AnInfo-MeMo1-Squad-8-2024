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
/*
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Task> tasks = new HashSet<>();
*/
    public Project(){
    }

    public Project(String title, String description ) {
        this.title = title;
        this.description = description;
        this.state = ProjectState.OPEN;
        this.startDateTime = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public Long getAssignedLeader() {
        return this.assignedLeader;
    }

    public ProjectState getState() {
        return this.state;
    }

    public LocalDateTime getStartDate() {
        return this.startDateTime;
    }

    public void setStartDate(LocalDateTime start_date) {
        this.startDateTime = start_date;
    }

    public LocalDateTime getFinishDate() {
        return this.finishDateTime;
    }
/*
    public Set<Task> getTasks() {
        return this.tasks;
    }
*/

    public void setState(ProjectState state) {
        this.state = state;
    }

    public void assignLeader(Long assignedLeader) {
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
/*
    public void setTask(Task task) {
        this.tasks.add(task);
    }

    public void removeTask(Task task) {
        //should destroy task
        tasks.remove(task);
    }
*/
}
