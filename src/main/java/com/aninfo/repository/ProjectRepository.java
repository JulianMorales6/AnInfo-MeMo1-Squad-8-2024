package com.aninfo.repository;

import com.aninfo.model.Project;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProjectRepository extends CrudRepository<Project, Long> {

    Project findProjectById(Long id);

    @Override
    List<Project> findAll();

}
