package com.terrasdevastadas.site.security;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.terrasdevastadas.site.model.Usuario;

public class CustomUserDetails implements UserDetails, Serializable {
	
	 private String email;
	 
	 private String senha;
	 
	 private Collection<? extends GrantedAuthority> authorities;
	 
	   
	 public CustomUserDetails (Usuario usuario){
	        this.email = usuario.getEmail();
	        this.senha = usuario.getSenha();
	    }

	 public CustomUserDetails(){}

	 @Override
	 public Collection<? extends GrantedAuthority> getAuthorities() {
	       
	        return authorities;
	    }

	 @Override
	 public String getPassword() {
	       
	        return senha;
	    }

	 @Override
	 public String getUsername() {
	        
	        return email;
	    }

	 @Override
	 public boolean isAccountNonExpired() {
	        
	        return true;
	    }

	 @Override
	 public boolean isAccountNonLocked() {
	        
	        return true;
	    }

	 @Override
	 public boolean isCredentialsNonExpired() {
	  
	        return true;
	    }

	 @Override
	 public boolean isEnabled() {

	        return true;
	    }


}
