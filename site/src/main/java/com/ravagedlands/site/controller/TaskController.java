package com.ravagedlands.site.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ravagedlands.site.dto.TaskDTO;
import com.ravagedlands.site.model.Task;
import com.ravagedlands.site.service.TaskService;


@RequestMapping(value = "/api/user")
@RestController 
public class TaskController {
	
	@Autowired  
    private TaskService taskService;

    
    @GetMapping(value = "/tasks/{id}")
    public ResponseEntity<Optional<Task>> getTaskById(@PathVariable Long id){
    	
    	Optional<Task> task = taskService.findAtividadeById(id);
    	return new ResponseEntity<>(task, task == null  || task.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }
    
	
    @PostMapping(value = "/{idUser}/tasks")
    public ResponseEntity<Optional<Task>> addTask(@PathVariable Long idUser, @RequestBody Task task){

    	Optional<Task> newAtividade = taskService.createTask(idUser, task);
    	return new ResponseEntity<>(newAtividade, newAtividade == null  || newAtividade.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }
    
    @PutMapping(value = "/task/{id}")
    public ResponseEntity<Task> updateTask(@RequestBody TaskDTO taskDTO, @PathVariable Long id){

        return new ResponseEntity<>(taskService.updateAtividade(taskDTO, id), HttpStatus.OK);
    }
    
  
    @DeleteMapping(value = "/tasks/delete/{id}")
    public ResponseEntity<List<Task>> deleteTaskById(@PathVariable Long id){

    	taskService.deleteTaskById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

