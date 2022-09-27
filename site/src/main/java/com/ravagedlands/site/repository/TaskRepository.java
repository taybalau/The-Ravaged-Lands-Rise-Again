package com.ravagedlands.site.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ravagedlands.site.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
	
	
}
