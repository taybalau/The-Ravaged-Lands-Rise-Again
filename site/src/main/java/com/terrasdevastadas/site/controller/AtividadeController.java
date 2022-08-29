package com.terrasdevastadas.site.controller;

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

import com.terrasdevastadas.site.model.Atividade;
import com.terrasdevastadas.site.service.AtividadeService;


@RequestMapping(value = "/api/usuarios")
@RestController 
public class AtividadeController {
	
	@Autowired  
    private AtividadeService atividadeService;

    @GetMapping(value = "/atividades")
    public ResponseEntity<List<Atividade>> getAllAtividades(){
    	
    	List<Atividade> atividade = atividadeService.findAllAtividades();
    	return new ResponseEntity<>(atividade, atividade == null  || atividade.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }
    
    @GetMapping(value = "/atividades/{id}")
    public ResponseEntity<Optional<Atividade>> getAtividadeById(@PathVariable Long id){
    	
    	Optional<Atividade> atividade = atividadeService.findAtividadeById(id);
    	return new ResponseEntity<>(atividade, atividade == null  || atividade.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }
    
    @GetMapping(value = "/{idUsuario}/atividades")
    public ResponseEntity<List<Atividade>> getAtividadesByUsuarioId(@PathVariable Long idUsuario){
    	
    	List<Atividade> atividades = atividadeService.findAtividadesByUsuarioId(idUsuario);
    	return new ResponseEntity<>(atividades, atividades == null  || atividades.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }
	
    @PostMapping(value = "/{idUsuario}/atividades")
    public ResponseEntity<Optional<Atividade>> addAtividade(@PathVariable Long idUsuario, @RequestBody Atividade atividade){

    	Optional<Atividade> newAtividade = atividadeService.createAtividade(idUsuario, atividade);
    	return new ResponseEntity<>(newAtividade, newAtividade == null  || newAtividade.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }
    
    @PutMapping(value = "/atividades/{id}")
    public ResponseEntity<Atividade> updateAtividade(@RequestBody Atividade atividade, @PathVariable Long id){

        return new ResponseEntity<>(atividadeService.updateAtividade(atividade, id), HttpStatus.OK);
    }
    
    @PutMapping(value="/{id}/pontos")
    public ResponseEntity<Atividade> pontosUsuario(@RequestBody Atividade atividade, @PathVariable Long id){

        return new ResponseEntity<>(atividadeService.somaPontos(atividade, id), HttpStatus.OK);
    }
    
    @DeleteMapping(value = "/atividades/{id}")
    public ResponseEntity<List<Atividade>> deleteAtividadeById(@PathVariable Long id){

    	atividadeService.deleteAtividadeById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

