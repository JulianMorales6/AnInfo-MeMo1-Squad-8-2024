package com.aninfo.service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aninfo.model.Project;
import com.aninfo.model.ProjectState;
import com.aninfo.repository.ProjectRepository;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project createProject(Project Project) {
        Project.setStartDate(LocalDateTime.now());
        if( Project.getState() == null ) {
            Project.setState(ProjectState.OPEN);
        }
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

    public void assignLeader(Long project_id, Long assigned_leader) {
        Project project = projectRepository.findProjectById(project_id);
        project.assignLeader(assigned_leader);
        projectRepository.save(project);
    }

    public void changeState(Long project_id, String state) {
        Project project = projectRepository.findProjectById(project_id);

        if (state.equals(ProjectState.BLOCKED.toString())) {
            project.setState(ProjectState.BLOCKED);
        }
        else if (state.equals(ProjectState.CLOSED.toString())) {
            project.setState(ProjectState.CLOSED);
        }
        else if (state.equals(ProjectState.FINISHED.toString())) {
            project.finish();
        }
        projectRepository.save(project);
    }

    public void changeTitle(Long project_id, String title) {
        Project project = projectRepository.findProjectById(project_id);
        project.setTitle(title);
        projectRepository.save(project);
    }

    public void changeDescription(Long project_id, String description) {
        Project project = projectRepository.findProjectById(project_id);
        project.setDescription(description);
        projectRepository.save(project);
    }
}