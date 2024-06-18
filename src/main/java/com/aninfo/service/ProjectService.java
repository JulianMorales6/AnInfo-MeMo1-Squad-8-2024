package com.aninfo.service;

import com.aninfo.model.Project;
import com.aninfo.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.Optional;

import java.time.LocalDateTime;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project createProject(Project Project) {
        Project.setStartDate(LocalDateTime.now());
        return projectRepository.save(Project);
    }

    public Collection<Project> getProjects() {
        return projectRepository.findAll();
    }

    public Optional<Project> findById(Long cbu) {
        return projectRepository.findById(cbu);
    }

    public void save(Project Project) {
        projectRepository.save(Project);
    }

    public void deleteById(Long cbu) {
        projectRepository.deleteById(cbu);
    }
}