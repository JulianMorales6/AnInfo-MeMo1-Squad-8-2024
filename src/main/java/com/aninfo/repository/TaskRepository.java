package com.aninfo.repository;

import com.aninfo.model.Task;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Collection<Task> findByProjectId(Long projectId);
    Task findTaskById(Long id);

    @Override
    List<Task> findAll();
}
