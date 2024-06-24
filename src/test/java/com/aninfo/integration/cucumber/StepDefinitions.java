package com.aninfo.integration.cucumber;

import java.util.Collection;

import org.junit.After;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.beans.factory.annotation.Autowired;

import com.aninfo.model.Project;
import com.aninfo.model.Task;
import com.aninfo.service.ProjectService;
import com.aninfo.service.ResourceService;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitions{

    @Autowired
    private ProjectService projectService;
    @Autowired
    private ResourceService resourceService;

    private Project project = null;
    private Project mock_project = new Project("title","description");
    private Task task = null;

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

    @Given("There is a leader with id {long}")
    public void resource_with_id_exists(Long resource_id) {
        assertEquals(true, resourceService.resourceExists(resource_id)); 
    }

    @Given("There is a project with a leader with id {long}")
    @When("I assign the leader with id {long} to project")
    public void assign_resource_to_project(Long resource_id) {
        if(resourceService.resourceExists(resource_id)) {
            mock_project.assignLeader(resource_id);
        }
    }

    @Then("Leader with id {long} is assigned to project")
    public void project_has_leader(Long resource_id) {
        assertEquals(resource_id, mock_project.getAssignedLeader());
    }

    @Given("There is not a leader with id {long}")
    public void resource_with_id_does_not_exist(Long resource_id) {
        assertEquals(false, resourceService.resourceExists(resource_id)); 
    }
    
    @Then("Leader with id {long} is not assigned to project")
    public void project_does_not_have_leader(Long resource_id) {
        assertEquals(null, mock_project.getAssignedLeader());
    }

    @Given("There is a project with id 1") 
    public void there_is_project(Long project_id) {
        assertEquals(1L, projectService.findById(project_id));
    }

    @After
    public void tearDown() {
        System.out.println("Tearing down after scenario execution...");
        mock_project = new Project("title","description");
    }

}
