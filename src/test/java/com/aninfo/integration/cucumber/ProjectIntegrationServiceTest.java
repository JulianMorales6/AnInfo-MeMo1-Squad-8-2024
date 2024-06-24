package com.aninfo.integration.cucumber;

import org.junit.Test;
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
    Project createProject(String title, String description) {
        return projectService.createProject(new Project(title, description));
    }
}
