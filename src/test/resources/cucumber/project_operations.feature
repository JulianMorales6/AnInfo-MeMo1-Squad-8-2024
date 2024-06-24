Feature: Project operations
  Checking project operations

  Scenario: Create a project
    Given There are no projects in the repository
    When I create a project with title Quantum Dawn and computing solutions for secure data processing description
    Then Project is created Successfully
    And Project name is Quantum Dawn
    And Project description is computing solutions for secure data processing

  Scenario: Assign leader to project successfully
    Given There is a resource with id 1
    And There is a project with id 1
    When I assign resource with id 4 to project with id 1
    Then Resource with id 1 is assigned as a leader in project 1

  Scenario: Cannot assign leader when it does not exist
    Given There is not a resource with id 4
    And There is a project with id 1
    When I assign resource with id 4 to project with id 1
    Then Resource with id 4 is not assigned as a leader in project 1
