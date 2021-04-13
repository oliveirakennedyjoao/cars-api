package com.jkoliveira.carros.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.PostMapping;

//import com.jkoliveira.carros.config.security.SecurityConfiguration;
import com.jkoliveira.carros.dtos.JwtTokenDTO;
import com.jkoliveira.carros.dtos.UserCredentialsDTO;
import com.jkoliveira.carros.models.User;
import com.jkoliveira.carros.repositories.UserRepository;

@Service
public class AuthService implements UserDetailsService{
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JwtTokenService jwtTokenService;
	
	@Autowired
	private UserRepository userRepository;
		
	public JwtTokenDTO validateUserCredentials(UserCredentialsDTO userCredentialsDTO){
		
		UsernamePasswordAuthenticationToken userCredentialsToken = null;			
		return new JwtTokenDTO(jwtTokenService.generate(userCredentialsToken), "bearer");
	}
	
	public JwtTokenDTO authenticate(UsernamePasswordAuthenticationToken userCredentialsToken){								
		try {
			Authentication auth = authManager.authenticate(userCredentialsToken);
			return new JwtTokenDTO(jwtTokenService.generate(auth), "Bearer");
		} catch (Exception e) {
			throw new BadCredentialsException("Bad Credentials");
		}		
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> signedUser = userRepository.findById(username);
		
		if(signedUser.isPresent()) {
			return signedUser.get();
		}
		
		throw new UsernameNotFoundException("Invalid User!");
		
	}
}
