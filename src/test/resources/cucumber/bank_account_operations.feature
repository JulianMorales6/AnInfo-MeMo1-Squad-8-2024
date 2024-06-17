Feature: Project operations
  Checking project operations

  Scenario: Creo un proyecto
    Given There is no proyects in the repository
    When I create a proyect with title 123321 and null description
    Then Project is created Successfully