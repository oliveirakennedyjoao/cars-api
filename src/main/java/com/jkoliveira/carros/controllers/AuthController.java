package com.jkoliveira.carros.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jkoliveira.carros.dtos.JwtTokenDTO;
import com.jkoliveira.carros.dtos.UserCredentialsDTO;
import com.jkoliveira.carros.services.AuthService;

@RestController
@RequestMapping("/signin")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@CrossOrigin
	@PostMapping	
	public ResponseEntity<JwtTokenDTO> authenticate(@RequestBody UserCredentialsDTO userCredentialsDTO){
		try {					
			return ResponseEntity.ok(authService.authenticate(new UsernamePasswordAuthenticationToken(userCredentialsDTO.getLogin(), userCredentialsDTO.getPassword())));
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
