package com.ravagedlands.site.controller;

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

import com.ravagedlands.site.dto.UserDTO;
import com.ravagedlands.site.dto.UserLoginDTO;
import com.ravagedlands.site.model.User;
import com.ravagedlands.site.service.UserService;


@RequestMapping(value = "/api/user")
@RestController 
public class UserController {
	
	@Autowired 
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUser(){

        List<User> user = userService.getAllUser();
        return new ResponseEntity<>(user, user == null  || user.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }
    
    @GetMapping(value = {"/{id}"})
    public ResponseEntity<Optional<User>> getById(@PathVariable Long id){

        Optional<User> user= userService.getUserById(id);
        
        return new ResponseEntity<>(user, user == null  ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }
    
    
    @PostMapping(value = "/signup")
    public ResponseEntity<User> addUser(@RequestBody UserDTO userDTO){

       return new ResponseEntity<>(userService.signUpUser(userDTO), HttpStatus.OK);
    }
    
    @PostMapping("/signin")
	public ResponseEntity<Optional<UserLoginDTO>> login(@RequestBody Optional<UserLoginDTO> user) {
		
    	Optional<UserLoginDTO> loginUser = userService.validCredential(user);
    	
    	return new ResponseEntity<>(loginUser, loginUser == null  || loginUser.isEmpty() ? HttpStatus.UNAUTHORIZED : HttpStatus.OK);
	}	
    
    @PutMapping(value="/{id}")
    public ResponseEntity<User> update(@RequestBody User user, @PathVariable Long id){

        return new ResponseEntity<>(userService.update(user, id), HttpStatus.OK);
    }
    
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<List<User>> deleteById(@PathVariable Long id){

        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
