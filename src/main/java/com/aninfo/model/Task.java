package com.aninfo.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.Duration;
import java.time.LocalDate;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.tomcat.jni.Local;

import com.aninfo.service.TicketSeverityService;

//import ch.qos.logback.core.util.Duration;

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

    private Long DaysToComplete;

    @Enumerated(EnumType.STRING)
    private TaskPriority priority;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ElementCollection
    @CollectionTable(name = "task_ticket_associations", joinColumns = @JoinColumn(name = "task_id"))
    private List<TicketAssociation> associatedTickets = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public Task(){
        this.DaysToComplete = -1L;
    }

    public Task(String title, String description, Project project) {
        this.title = title;
        this.description = description;
        this.state = TaskState.OPEN;
        this.startDateTime = LocalDateTime.now();
        this.project = project;
    }

    public Task(String title, String description, TaskPriority priority, Project project) {
        this.title = title;
        this.description = description;
        this.state = TaskState.OPEN;
        this.startDateTime = LocalDateTime.now();
        this.priority = priority;
        this.project = project;
    }

    public Task(String title, String description, Long assignedEmployee, TaskPriority priority, Project project) {
        this.title = title;
        this.description = description;
        this.state = TaskState.OPEN;
        this.assignedEmployee = assignedEmployee;
        this.startDateTime = LocalDateTime.now();
        this.priority = priority;
        this.project = project;
    }

    public String getTitle() {
        return this.title;
    }
    
    public String getDescription() {
        return this.description;
    }

    public Long getAssignedEmployee() {
        return this.assignedEmployee;
    }

    public TaskState getState() {
        return this.state;
    }

    public LocalDateTime getStartDate() {
        return this.startDateTime;
    }

    public LocalDateTime getFinishDate() {
        return this.finishDateTime;
    }

    public TaskPriority getPriority() {
        return this.priority;
    }

    public void assignEmployee(Long assignedEmployee) {
        this.assignedEmployee = assignedEmployee;
        this.state = TaskState.PROGRESS;
    }

    public void setProject(Project project) {
       this.project = project;
    }

    public void setState(TaskState state) {
        this.state = state;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public void finish() {
        this.state = TaskState.FINISHED;
        this.finishDateTime = LocalDateTime.now();
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public List<TicketAssociation> getAssociatedTickets() {
        return this.associatedTickets;
    }

    public void setDaysToComplete(Long severityDays, LocalDateTime ticketAssociationDate){        

        if(severityDays != -1){
            LocalDateTime adjustedDate = ticketAssociationDate.plusDays(severityDays);
            Duration d = Duration.between(LocalDateTime.now(), adjustedDate);
            this.DaysToComplete = d.toDays();
        }
        else
            this.DaysToComplete = -1L;
    }

    public long getDaysToComplete(){
        return this.DaysToComplete;
    }
    
    public LocalDateTime getFirstTicketDate(){

        if (this.associatedTickets.isEmpty())
            return null;

        return this.associatedTickets.get(0).getAssociationDate();
    }

    public Long getFirstTicketId(){

        if (this.associatedTickets.isEmpty())
            return -1L;

        return this.associatedTickets.get(0).getTicketId();
    }

    public void associateTicket(Long associatedTicket) {
        if (!isTicketAssociated(associatedTicket)) {
            TicketAssociation association = new TicketAssociation(associatedTicket, LocalDateTime.now());
            this.associatedTickets.add(association);
        }  
    }
    
    public void disassociateTicket(Long disassociatedTicket) {
        this.associatedTickets.removeIf(association -> association.getTicketId().equals(disassociatedTicket));
    }

    public boolean isTicketAssociated(Long ticketId) {
        return associatedTickets.stream().anyMatch(association -> association.getTicketId().equals(ticketId));
    }

    @Embeddable
    public static class TicketAssociation {
        private Long ticketId;
        private LocalDateTime associationDate;

        public TicketAssociation() {
        }

        public TicketAssociation(Long ticketId, LocalDateTime associationDate) {
            this.ticketId = ticketId;
            this.associationDate = associationDate;
        }

        public Long getTicketId() {
            return this.ticketId;
        }

        public void setTicketId(Long ticketId) {
            this.ticketId = ticketId;
        }

        public LocalDateTime getAssociationDate() {
            return this.associationDate;
        }

        public void setAssociationDate(LocalDateTime associationDate) {
            this.associationDate = associationDate;
        }
    }

}
