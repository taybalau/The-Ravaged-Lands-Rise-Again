package com.ravagedlands.site.security;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ravagedlands.site.model.User;

public class CustomUserDetails implements UserDetails, Serializable {
	
	 private String email;
	 
	 private String password;
	 
	 private Collection<? extends GrantedAuthority> authorities;
	 
	   
	 public CustomUserDetails (User user){
	        this.email = user.getEmail();
	        this.password = user.getPassword();
	    }

	 public CustomUserDetails(){}

	 @Override
	 public Collection<? extends GrantedAuthority> getAuthorities() {
	       
	        return authorities;
	    }

	 @Override
	 public String getPassword() {
	       
	        return password;
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
