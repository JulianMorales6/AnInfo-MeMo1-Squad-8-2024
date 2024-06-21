package com.aninfo;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.aninfo.model.Project;
import com.aninfo.model.Task;
import com.aninfo.service.ProjectService;
import com.aninfo.service.TaskService;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.web.bind.annotation.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@SpringBootApplication
@EnableSwagger2
public class ProyectApp {

	@Autowired
    private ProjectService projectService;
    @Autowired
    private TaskService taskService;

    @PostMapping("/projects")
    @ResponseStatus(HttpStatus.CREATED)
    public Project creaProject(@RequestBody Project project) {
        return projectService.createProject(project);
    }

    @GetMapping("/projects")
    public Collection<Project> getProjects() {
        return projectService.getProjects();
    }
    


    @PostMapping("/projects/{project_id}/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public Task addTaskToProject(@PathVariable Long project_id, @RequestBody Task task) {
        
        Optional<Project> projectOpt = projectService.findById(project_id);
        Project project = projectOpt.get();
        task.setProject(project);
        return taskService.createTask(task);
    }


    @GetMapping("/tasks")
    public Collection<Task> getTasks() {
        return taskService.getTasks();
    }

    @PutMapping("/projects")
    public ResponseEntity<Project> updateProject(
            @RequestParam(name = "project_id", required = true) Long project_id,
            @RequestParam(name = "assigned_leader", required = false) Long assigned_leader,
            @RequestParam(name = "state", required = false) String state) {
        
        if (assigned_leader != null)
            projectService.assignLeader(project_id, assigned_leader);
  
        if (state != null)
            projectService.changeState(project_id, state);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/tasks")
    public ResponseEntity<Project> updateTask(
            @RequestParam(name = "task_id", required = true) Long task_id,
            @RequestParam(name = "assigned_employee", required = false) Long assigned_employee,
            @RequestParam(name = "state", required = false) String state,
            @RequestParam(name = "priority", required = false) String priority) {
        
        if (assigned_employee != null)
            taskService.assignEmployee(task_id, assigned_employee);
  
        if (state != null)
            taskService.changeState(task_id, state);        
        if (priority != null)
            taskService.changePriority(task_id, priority);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/tasks/{task_id}/{assigned_employee}")
	public ResponseEntity<Project> updateTaskEmployee(@PathVariable Long task_id , @PathVariable Long assigned_employee) {

        taskService.assignEmployee(task_id, assigned_employee);
        return ResponseEntity.ok().build();
	}

	public static void main(String[] args) {
		SpringApplication.run(ProyectApp.class, args);
	}

	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
			.select()
			.apis(RequestHandlerSelectors.any())
			.paths(PathSelectors.any())
			.build();
	}
}
