package com.terrasdevastadas.site.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.terrasdevastadas.site.model.Atividade;
import com.terrasdevastadas.site.model.Usuario;
import com.terrasdevastadas.site.repository.AtividadeRepository;
import com.terrasdevastadas.site.repository.UsuarioRepository;

@Service
public class AtividadeService {
	
	@Autowired
	private AtividadeRepository atividadeRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public List<Atividade> findAllAtividades(){

	        return atividadeRepository.findAll();
	    }
	 
	 public Optional<Atividade> findAtividadeById(Long id){

	        return atividadeRepository.findById(id);
	    }
	 
	 public List<Atividade> findAtividadesByUsuarioId(Long idUsuario){

	        return atividadeRepository.findAtividadeByUsuarioId(idUsuario);
	    }
	 
	 
	  public Optional<Atividade> createAtividade(Long idUsuario, Atividade novaAtividade){
		  
		     Optional<Atividade> atividade = usuarioRepository.findById(idUsuario).map(usuario -> {
		       novaAtividade.setUsuario(usuario);
		       return atividadeRepository.save(novaAtividade);
		    });
		  
		     return atividade;
		 }
	  
	  public Atividade updateAtividade(Atividade atividade, Long id){

		     Atividade newAtividade = atividadeRepository.findById(id).get();

	         newAtividade.setConcluida(atividade.isConcluida());
	         newAtividade.setNome(atividade.getNome());
	         newAtividade.setDescrição(atividade.getDescrição());
	         newAtividade.setData(atividade.getData());

	         return atividadeRepository.save(newAtividade);
	  }
	  
	  
	    public Atividade somaPontos(Atividade atividade, Long id){
	    	
	    	Atividade outraAtividade = updateAtividade(atividade, id);
	  	  
	    	Usuario novoUsuario = new Usuario();
	    	
	    	Usuario usuario = outraAtividade.getUsuario();
	    	
				if (atividade.isConcluida()){
			
					novoUsuario.setId(usuario.getId());
			  
					novoUsuario.setPontos(usuario.getPontos() + 1);
					
					usuarioRepository.save(novoUsuario);
							
					return outraAtividade;
				}
				
				return outraAtividade;
				
			}
	    
		
	 	 
	  public void deleteAtividadeById (Long id){

		  atividadeRepository.deleteById(id);
	  }
	  

}
