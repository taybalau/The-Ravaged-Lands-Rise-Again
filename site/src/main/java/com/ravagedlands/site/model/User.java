package com.ravagedlands.site.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name="tb_users", schema="ravaged_lands") 
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Long id;

	@NotNull 
	private String name;
	
	@NotNull
	private String email;
	
	@NotNull
	private String password;
	
	@Value("${some.key:0}")
	private Long score;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="user")
	@JsonManagedReference
	private List<Task> tasks;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getScore() {
		return score;
	}

	public void setScore(Long score) {
		this.score = score;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	

}
