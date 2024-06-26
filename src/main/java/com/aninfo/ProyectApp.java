package com.aninfo;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.aninfo.model.AssociationDTO;
import com.aninfo.model.Project;
import com.aninfo.model.Resource;
import com.aninfo.model.Task;
import com.aninfo.service.ProjectService;
import com.aninfo.service.ResourceService;
import com.aninfo.service.TaskService;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;



@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@SpringBootApplication
@EnableSwagger2
public class ProyectApp {

	@Autowired
    private ProjectService projectService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private ResourceService resourceService;

    @PostMapping("/projects/new")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createProject(@RequestBody Project project) {
        if(project.getAssignedLeader() != null && !resourceService.resourceExists(project.getAssignedLeader())) {
            return new ResponseEntity<>("Assigned resource does not exist",HttpStatus.BAD_REQUEST);
        } else {        
            return new ResponseEntity<>(projectService.createProject(project), HttpStatus.CREATED);
        }
    }

    @GetMapping("/projects")
    public Collection<Project> getProjects() {
        return projectService.getProjects();
    }

    @GetMapping("projects/{project_id}")
    public ResponseEntity<?> getProjectById(@PathVariable Long project_id) {
        Optional<Project> projectOpt = projectService.findById(project_id);
            return projectOpt.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/projects/{project_id}/tasks/new")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addTaskToProject(
            @PathVariable Long project_id, 
            @RequestBody Task task) {
        
        if(task.getAssignedEmployee() != null && !resourceService.resourceExists(task.getAssignedEmployee())) {
            return new ResponseEntity<>("Assigned resource does not exist",HttpStatus.BAD_REQUEST);
        }
        Optional<Project> projectOpt = projectService.findById(project_id);
        if(projectOpt.isPresent()) {
            Project project = projectOpt.get();
            task.setProject(project);
        } else {
            return new ResponseEntity<>("Related project does not exist",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(taskService.createTask(task), HttpStatus.CREATED);
    }

    @GetMapping("/tasks")
    public Collection<Task> getTasks() {
        return taskService.getTasks();
    }

    @GetMapping("/proyect/{project_id}/tasks/get")
    public Collection<Task> getTasksFromProject(@PathVariable Long project_id) {

        return taskService.getTasksFromProject(project_id);
    }
    
    @GetMapping("/tasks/")
    public ResponseEntity<?> getTasksById(@RequestParam String ids) {

        String[] idsArray = ids.split(",");
        List<Long> taskIds = Arrays.stream(idsArray)
                                .map(Long::parseLong)
                                .collect(Collectors.toList());
        List<Task> taskList = taskService.getTasksByIds(taskIds);

        if (taskList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(taskList, HttpStatus.OK);
        }
    }

    @PatchMapping("/tasks/associations/update")
    public ResponseEntity<?> associateTicketToTasks(@RequestBody AssociationDTO associationDTO){
        
        taskService.updateTicketAssociation(associationDTO);
        return ResponseEntity.ok().build();
        }

    @PatchMapping("/projects/{project_id}")
    public ResponseEntity<Project> updateProject(
            @PathVariable Long project_id,
            @RequestParam(name = "assigned_leader", required = false) Long assigned_leader,
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "description", required = false) String description,
            @RequestParam(name = "state", required = false) String state) {
        
        if (assigned_leader != null)
            if(!resourceService.resourceExists(assigned_leader)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                projectService.assignLeader(project_id, assigned_leader);
            }
        if (state != null)
            projectService.changeState(project_id, state);

        if (title != null)
            projectService.changeTitle(project_id, title);

        if (description != null)
            projectService.changeDescription(project_id, description);
            
        return ResponseEntity.ok().build();
        }

    @GetMapping("/resources")
    public List<Resource> getAllResources() {
        return resourceService.getResources();
    }

    @PatchMapping("/tasks/{task_id}")
    public ResponseEntity<Project> updateTask(
            @PathVariable Long task_id,
            @RequestParam(name = "assigned_employee", required = false) Long assigned_employee,
            @RequestParam(name = "state", required = false) String state,
            @RequestParam(name = "priority", required = false) String priority) {
        
        if (assigned_employee != null)
            if(!resourceService.resourceExists(assigned_employee)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                taskService.assignEmployee(task_id, assigned_employee);
            }
        if (state != null)
            taskService.changeState(task_id, state);        
        if (priority != null)
            taskService.changePriority(task_id, priority);

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

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
