package com.aninfo;

import com.aninfo.model.Task;
import com.aninfo.model.Project;
import com.aninfo.service.ProjectService;
import com.aninfo.service.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
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

	@PostMapping("/projects")
	@ResponseStatus(HttpStatus.CREATED)
	public Project creaProject(@RequestBody Project project) {
		return projectService.createProject(project);
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
