package com.aninfo.integration.cucumber;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.aninfo.model.Project;
import com.aninfo.service.ProjectService;
import com.aninfo.service.ResourceService;

@RunWith(SpringRunner.class)
@SpringBootTest

public class ProjectIntegrationServiceTest {

    @Autowired
    ProjectService projectService;

    @Autowired
    ResourceService resourceService;

    @Test
    public void testCreateProject() {
        String title = "New Project";
        String description = "Project Description";
        Project project = projectService.createProject(new Project(title, description));
        assertNotNull(project);
        assertNotNull(project.getId());
    }

}
