package com.terrasdevastadas.site.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UsuarioLoginDTO {
	
	private String nome;
	
	@NotBlank 
	@Email   
	private String email;
	
	@NotBlank
	private String senha;
	
	private String token;
	    
	public UsuarioLoginDTO(){
		
	   }
	  
	public UsuarioLoginDTO(String email, String senha){
	        this.email = email;
	        this.senha = senha;
	   }

	public String getEmail() {
	        return this.email;
	   }

	public void setEmail(String email) {
	        this.email = email;
	   }

	public String getSenha() {
	        return this.senha;
	   }

	public void setSenha(String senha) {
	        this.senha = senha;
	   }
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	

}
