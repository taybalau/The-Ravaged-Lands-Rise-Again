package com.terrasdevastadas.site.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;


public class UsuarioDTO {
	
	
	@NotNull
	private String nome;
	
	@NotNull
	@Email 
	private String email;
	
	@NotNull
	private String senha;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

}
