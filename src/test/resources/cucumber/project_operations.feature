Feature: Project operations
  Checking project operations

  Scenario: Create a project
    Given There are no projects in the repository
    When I create a project with title "Quantum Dawn" and "computing solutions for secure data processing" description
    Then Project is created Successfully
    And Project name is "Quantum Dawn"
    And Project description is "computing solutions for secure data processing"

  Scenario: Successfully assign leader to project
    Given There is a leader with id 1
    When I assign the leader with id 1 to project
    Then Leader with id 1 is assigned to project

  Scenario: Cannot assign unavailable leader to project
    Given There is not a leader with id 4
    When I assign the leader with id 4 to project
    Then Leader with id 4 is not assigned to project

  Scenario: Change leader of project
    Given There is a project with a leader with id 1
    When I assign the leader with id 2 to project
    Then Leader with id 2 is assigned to project