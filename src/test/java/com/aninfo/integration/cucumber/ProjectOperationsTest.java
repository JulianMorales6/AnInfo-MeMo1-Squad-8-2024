package com.aninfo.integration.cucumber;

import com.aninfo.model.Project;

//import com.aninfo.exceptions.DepositNegativeSumException;
//import com.aninfo.exceptions.InsufficientFundsException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
//import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.description;

public class ProjectOperationsTest extends ProjectIntegrationServiceTest {

    private Project project;

    @Before
    public void setup() {
        System.out.println("Before any test execution");
    }

    @Given("^There is no proyects in the repository")
    
    @When("^I create a proyect with title (\\d+)$ and null description")
    public void create_a_proyect_with_title(int numero) {

        System.out.println("hihihihi");
        System.out.format("Cukes: %n\n", numero);
        project = createProject("test123", "");
    }

    @Then("^Project is created Successfully")
    public void project_not_null(Project project) {
        assertEquals(project.getTitle(), "test123" );
    }

    /*
        try {
            project = 
        } catch (InsufficientFundsException ife) {
            //this.ife = ife;
        }
    }
    
    @When("^Trying to deposit (.*)$")
    public void trying_to_deposit(int sum) {
        try {
            project = deposit(project, Double.valueOf(sum));
        } catch (DepositNegativeSumException dnse) {
            this.dnse = dnse;
        }
    }

    @Then("^Project balance should be (\\d+)$")
    public void project_balance_should_be(int balance) {
        assertEquals(Double.valueOf(balance), project.getBalance());
    }

    @Then("^Operation should be denied due to insufficient funds$")
    public void operation_should_be_denied_due_to_insufficient_funds() {
        assertNotNull(ife);
    }

    @Then("^Operation should be denied due to negative sum$")
    public void operation_should_be_denied_due_to_negative_sum() {
        assertNotNull(dnse);
    }

    @And("^Project balance should remain (\\d+)$")
    public void project_balance_should_remain(int balance) {
        assertEquals(Double.valueOf(balance), project.getBalance());
    }
    */
    
    @After
    public void tearDown() {
        System.out.println("After all test execution");
    }
}
