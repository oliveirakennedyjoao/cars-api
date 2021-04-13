package com.jkoliveira.carros.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jkoliveira.carros.dtos.UserDTO;
import com.jkoliveira.carros.services.UserService;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {		
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> list() {
		try {
			return ResponseEntity.ok(userService.list());
		} catch (Exception e) {			
			return ResponseEntity.badRequest().build();
		}		
	}
	
	@PostMapping
	public ResponseEntity<Void> create(@RequestBody UserDTO user) { 
		userService.save(user);
		return ResponseEntity.ok().build(); 
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> get(@PathVariable("id") String login) { 
		try {			
			return ResponseEntity.ok(userService.get(login));
		} catch (NoSuchElementException e) {			
			return ResponseEntity.notFound().build();
		} catch (Exception e) {			
			return ResponseEntity.badRequest().build();
		}		 
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserDTO> update(@PathVariable("id") String login, @RequestBody UserDTO userDTO) { 
		userService.update(login, userDTO);
		return ResponseEntity.ok().build(); 
	}
	
	@DeleteMapping("/{id}")	
	public ResponseEntity<Void> delete(@PathVariable("id") String login) { 
		try {
			userService.delete(login);
			return ResponseEntity.ok().build();
		} catch (NoSuchElementException e) {			
			return ResponseEntity.notFound().build();
		} catch (Exception e) {			
			return ResponseEntity.badRequest().build();
		}				
	}
}
