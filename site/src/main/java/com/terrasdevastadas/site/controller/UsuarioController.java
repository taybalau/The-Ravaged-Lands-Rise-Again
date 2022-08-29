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

import com.terrasdevastadas.site.dto.UsuarioDTO;
import com.terrasdevastadas.site.dto.UsuarioLoginDTO;
import com.terrasdevastadas.site.model.Usuario;
import com.terrasdevastadas.site.service.UsuarioService;


@RequestMapping(value = "/api/usuario")
@RestController 
public class UsuarioController {
	
	@Autowired 
    private UsuarioService usuarioService;

    @GetMapping("/")
    public ResponseEntity<List<Usuario>> getAllUsuarios(){

        List<Usuario> usuario = usuarioService.getAllUsuarios();
        return new ResponseEntity<>(usuario, usuario == null  || usuario.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }
    
    @GetMapping(value = {"/{id}"})
    public ResponseEntity<Optional<Usuario>> getById(@PathVariable Long id){

        Optional<Usuario> usuario = usuarioService.getById(id);
        return new ResponseEntity<>(usuario, usuario == null  || usuario.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }
    
    
    @PostMapping(value = "/cadastrar")
    public ResponseEntity<Usuario> addUsuario(@RequestBody UsuarioDTO usuarioDTO){

       return new ResponseEntity<>(usuarioService.CadastrarUsuario(usuarioDTO), HttpStatus.OK);
    }
    
    @PostMapping("/login")
	public ResponseEntity<Optional<UsuarioLoginDTO>> Login(@RequestBody Optional<UsuarioLoginDTO> usuario) {
		
    	Optional<UsuarioLoginDTO> loginUsuario = usuarioService.validarCredential(usuario);
    	
    	return new ResponseEntity<>(loginUsuario, loginUsuario == null  || loginUsuario.isEmpty() ? HttpStatus.UNAUTHORIZED : HttpStatus.OK);
	}
	
    
    @PutMapping(value="/{id}")
    public ResponseEntity<Usuario> update(@RequestBody Usuario usuario, @PathVariable Long id){

        return new ResponseEntity<>(usuarioService.update(usuario, id), HttpStatus.OK);
    }
    
    @DeleteMapping(value = "/deletar/{id}")
    public ResponseEntity<List<Usuario>> deleteById(@PathVariable Long id){

        usuarioService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
