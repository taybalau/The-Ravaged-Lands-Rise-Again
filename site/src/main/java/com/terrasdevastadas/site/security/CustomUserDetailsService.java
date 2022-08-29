package com.terrasdevastadas.site.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.terrasdevastadas.site.model.Usuario;
import com.terrasdevastadas.site.repository.UsuarioRepository;

public class CustomUserDetailsService implements UserDetailsService {
	
//	@Autowired
//	private UsuarioRepository usuarioRepository;
//
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        
//        Optional<Usuario> usuario = usuarioRepository.findByEmail(username);
//        if (usuario.isPresent()) {
//            return new CustomUserDetails(usuario.get());
//
//        } else {
//           throw new UsernameNotFoundException("Usuário não encontrado"); 
//        }
//    }
	

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		Optional<Usuario> user = usuarioRepository.findByEmail(userName);
		user.orElseThrow(() -> new UsernameNotFoundException(userName + " not found."));

		return user.map(CustomUserDetails::new).get();
	}
	
	

}
