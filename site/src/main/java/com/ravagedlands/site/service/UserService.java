package com.ravagedlands.site.service;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ravagedlands.site.dto.UserDTO;
import com.ravagedlands.site.dto.UserLoginDTO;
import com.ravagedlands.site.model.User;
import com.ravagedlands.site.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	

	public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
	
	
	public List<User> getAllUser(){

	        return userRepository.findAll();
	    }
	 
	
	public Optional<User> getUserById(Long id){
		
		return userRepository.findById(id);
		
	    }
	 
	 
	public User signUpUser(UserDTO userDTO) {
	       Optional<User> user = userRepository.findByEmail(userDTO.getEmail());

	        if (user.isPresent()) {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This email is already being used");
	        } else {
	            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	            userDTO.setPassword(encoder.encode(userDTO.getPassword()));

	            User newUsew = new User();
	            		
	            newUsew.setName(userDTO.getName());
	            newUsew.setEmail(userDTO.getEmail());
	            newUsew.setPassword(userDTO.getPassword());

	            return userRepository.save(newUsew);
	        }
	    }
	
    
    public Optional<UserLoginDTO> validCredential(Optional<UserLoginDTO> userDTO) {
		
    	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    	
		Optional<User> user = userRepository.findByEmail(userDTO.get().getEmail());
		
		if(user.isPresent()) {
			if(encoder.matches(userDTO.get().getPassword(), user.get().getPassword())) {
				String auth = userDTO.get().getEmail() + ":" + userDTO.get().getPassword();
				
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);
				
				userDTO.get().setToken(authHeader);
				userDTO.get().setName(user.get().getName());
				
				return userDTO;
			}
		}
		
		return null;
	}
    
    
    public User update(User user, Long id){

		 User newUser = userRepository.findById(id).get();

	  
	     if (Objects.nonNull(user.getName()) && !"".equalsIgnoreCase(user.getName())) {
	    	 newUser.setName(user.getName());
	         }
	  
	     if (Objects.nonNull(user.getEmail()) && !"".equalsIgnoreCase(user.getEmail())) {
	    	 newUser.setEmail(user.getEmail());
	         }
	     
	     if (Objects.nonNull(user.getPassword()) && !"".equalsIgnoreCase(user.getPassword())) {
	    	 BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	    	 newUser.setPassword(encoder.encode(user.getPassword()));
         }
	     
	     if (Objects.nonNull(user.getScore())) {
	    	 newUser.setScore(user.getScore());
         }
	     
	        return userRepository.save(newUser);
		}
	 
	  
	 
	 public void delete (Long id){

	        userRepository.deleteById(id);
	   }

}
