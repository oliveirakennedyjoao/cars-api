package com.jkoliveira.carros.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jkoliveira.carros.models.User;
import com.jkoliveira.carros.repositories.UserRepository;
import com.jkoliveira.carros.services.JwtTokenService;

public class AuthSecurityFilter extends OncePerRequestFilter {
		
	private JwtTokenService jwtTokenService;
	
	private UserRepository userRepository;
	
	public AuthSecurityFilter(JwtTokenService jwtTokenService, UserRepository userRepository) {	
		this.jwtTokenService = jwtTokenService;
		this.userRepository = userRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = getTokenFromHeader(request);
		boolean isTokenValid = jwtTokenService.validate(token);
		
		if(isTokenValid) {
			authenticateUser(token);
		}
		
		filterChain.doFilter(request, response);
	}
	
	private void authenticateUser(String token) {		
		String userLogin = jwtTokenService.getUserId(token);
		User user = userRepository.findById(userLogin).get();
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, null);
		SecurityContextHolder.getContext().setAuthentication(auth);
	}
	
	private String getTokenFromHeader(HttpServletRequest request) {
		String tokenFromHeader = request.getHeader("Authorization");
		
		return tokenFromHeader == null || tokenFromHeader.isEmpty() || !tokenFromHeader.startsWith("Bearer ") ? 
				null : 
				tokenFromHeader.substring(7, tokenFromHeader.length());
	}
}
