package com.terrasdevastadas.site.model;

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


@Entity
@Table(name="tb_usuarios", schema="terras_devastadas") 
public class Usuario {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Long id;

	@NotNull 
	private String nome;
	
	@NotNull
	private String email;
	
	@NotNull
	private String senha;
	
	@Value("${some.key:0}")
	private Long pontos;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="usuario")
	private List<Atividade> atividades;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
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
	
	public Long getPontos() {
		return pontos;
	}
	
	public void setPontos(Long pontos) {
		this.pontos = pontos;
	}
	
	public List<Atividade> getAtividades() {
		return atividades;
	}
	
	public void setAtividades(List<Atividade> atividades) {
		this.atividades = atividades;
	}

}
