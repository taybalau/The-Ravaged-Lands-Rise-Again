package com.terrasdevastadas.site.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.terrasdevastadas.site.model.Atividade;

@Repository
public interface AtividadeRepository extends JpaRepository<Atividade, Long> {
	
	List<Atividade> findAtividadeByUsuarioId(Long idUsuario);
	

}
