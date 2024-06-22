package com.aninfo.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Enumerated(EnumType.STRING)
    private TaskPriority priority;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ElementCollection
    @CollectionTable(name = "task_ticket_associations", joinColumns = @JoinColumn(name = "task_id"))
    private List<TicketAssociation> associatedTickets = new ArrayList<>();

    //private list<Long, String> associatedTickets = new HashMap<>();
    //private List<Long> associatedTickets = new ArrayList<>();

    //@ElementCollection
    //@CollectionTable(name = "task_tickets", joinColumns = @JoinColumn(name = "task_id"))
    //@Column(name = "ticket_id")
    //private List<String> ticketIds = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public Task(){
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
/*
    public void setAssociatedTickets(List<Long> associatedTickets) {
        this.associatedTickets = associatedTickets;
    }
*/
    public void associateTicket(Long associatedTicket) {

        TicketAssociation association = new TicketAssociation(associatedTicket, LocalDateTime.now());
        this.associatedTickets.add(association);
    }
    
    public void disassociateTicket(Long disassociatedTicket) {
        this.associatedTickets.removeIf(association -> association.getTicketId().equals(disassociatedTicket));
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
            return ticketId;
        }

        public void setTicketId(Long ticketId) {
            this.ticketId = ticketId;
        }

        public LocalDateTime getAssociationDate() {
            return associationDate;
        }

        public void setAssociationDate(LocalDateTime associationDate) {
            this.associationDate = associationDate;
        }
    }

}
