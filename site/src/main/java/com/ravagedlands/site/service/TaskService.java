package com.ravagedlands.site.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ravagedlands.site.dto.TaskDTO;
import com.ravagedlands.site.model.Task;
import com.ravagedlands.site.model.User;
import com.ravagedlands.site.repository.TaskRepository;
import com.ravagedlands.site.repository.UserRepository;

@Service
public class TaskService {
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private UserRepository userRepository;
	

	 public Optional<Task> findAtividadeById(Long id){

	        return taskRepository.findById(id);
	    }
	 
	 
	  public Optional<Task> createTask(Long idUser, Task newTask){
		  
		     Optional<Task> task = userRepository.findById(idUser).map(user -> { 
		       newTask.setUser(user);
		       return taskRepository.save(newTask);
		    });
		  
		     return task;
		 }
	  
	  public Task updateAtividade(TaskDTO taskDTO, Long id){

		     Task newTask = taskRepository.findById(id).get();
		     
		     if (Objects.nonNull(taskDTO.isDone())) {
		    	 newTask.setDone(taskDTO.isDone());
		         }
		  
		     if (Objects.nonNull(taskDTO.getName()) && !"".equalsIgnoreCase(taskDTO.getName())) {
		    	 newTask.setName(taskDTO.getName());
		         }
		  
		     
		     if (Objects.nonNull(taskDTO.getDescription()) && !"".equalsIgnoreCase(taskDTO.getDescription())) {
		    	 newTask.setDescription(taskDTO.getDescription());
	         }
		    
		     User newUser = userRepository.findById(newTask.getUser_id()).get();
		     
		     taskRepository.save(newTask);
		  	  
			if (newTask.isDone()){
				
			newUser.setScore(newUser.getScore() + 1);
				 
			userRepository.save(newUser);
								
			}
		     
		    return taskRepository.save(newTask);
		         
	  }
		
	 	 
	  public void deleteTaskById (Long id){

		  taskRepository.deleteById(id);
	  }
	  

}
