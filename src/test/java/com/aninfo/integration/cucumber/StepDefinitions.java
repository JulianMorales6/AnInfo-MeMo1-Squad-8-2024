package com.aninfo.integration.cucumber;

import java.util.Collection;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.beans.factory.annotation.Autowired;

import com.aninfo.model.Project;
import com.aninfo.service.ProjectService;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitions{

    @Autowired
    private ProjectService projectService;
    
    private Project project = null;

    @Given("There are no projects in the repository")
    public void there_are_no_projects_in_the_repository() {
        Collection<Project> projects = projectService.getProjects();
        assertEquals(0, projects.size()-1);
    }

    @When("I create a project with title {string} and {string} description")
    public void create_a_project_with_title_and_description(String title, String description) {
        project = projectService.createProject(new Project(title,description));
    }

    @Then("Project is created Successfully")
    public void project_is_created_successfully() {
        assertNotNull(project);
    }

    @And("Project name is {string}")
    public void project_name_is(String title) {
        assertEquals(title, project.getTitle());
    }

    @And("Project description is {string}")
    public void project_description_is(String description) {
        assertEquals(description, project.getDescription());
    }

}
