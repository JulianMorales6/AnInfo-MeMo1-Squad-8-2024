Feature: Project operations
  Checking project operations

  Scenario: Create a project
    Given There are no projects in the repository
    When I create a project with title "Quantum Dawn" and "computing solutions for secure data processing" description
    Then Project is created Successfully
    And Project name is "Quantum Dawn"
    And Project description is "computing solutions for secure data processing"

