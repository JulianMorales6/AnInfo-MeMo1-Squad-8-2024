package com.aninfo.integration.cucumber;

import com.aninfo.ProyectApp;
import com.aninfo.model.Project;
import com.aninfo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

@ContextConfiguration(classes = ProyectApp.class)
@WebAppConfiguration
public class ProjectIntegrationServiceTest {

    @Autowired
    ProjectService projectService;

    Project createProject(String title, String description) {
        return projectService.createProject(new Project(title, description));
    }
}
