package com.aninfo.integration.cucumber;

import java.util.Collection;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;

import com.aninfo.model.Project;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
//import static org.mockito.Mockito.description;

public class ProjectOperationsTest extends ProjectIntegrationServiceTest {

    private Project project = null;

    @BeforeEach
    public void setup() {
        System.out.println("Before any test execution");
    }

    @Given("^There are no projects in the repository$")
    public void there_are_no_projects_in_the_repository() {
        Collection<Project> projects = projectService.getProjects();
        assertEquals(0, projects.size());
    }

    @When("I create a project with title {string} and description {string}")
    public void create_a_project_with_title_and_description(String title, String description) {
        project = createProject(title, description);
    }

    @Then("^Project is created Successfully$")
    public void project_is_created_successfully() {
        assertNotNull(project);
    }

    @And("^Project name is \"([^\"]*)\"$")
    public void project_name_is(String title) {
        assertEquals(title, project.getTitle());
    }

    @And("^Project description is \"([^\"]*)\"$")
    public void project_description_is(String description) {
        assertEquals(description, project.getDescription());
    }

    @AfterEach
    public void tearDown() {
        System.out.println("After all test execution");
    }
}
