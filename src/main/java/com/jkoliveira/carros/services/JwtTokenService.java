package com.jkoliveira.carros.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.jkoliveira.carros.models.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtTokenService {
	
	@Value("${cars.jwt.expiration}")
	private String expirationTime;
	
	@Value("${cars.jwt.secret}")
	private String secretKey;
	
	public String generate(Authentication authentication) {
		
		User signedUser = (User) authentication.getPrincipal();
		Date now = new Date();
		Date expirationDate = new Date(now.getTime() + new Long(expirationTime));
		
		return Jwts.builder()
				.setIssuer("Cars API")
				.setSubject(signedUser.getLogin())
				.setIssuedAt(now)
				.setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS256, secretKey)
				.compact();
	}
	
	public boolean validate(String token) {
		try {
			Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}				
	}
	
	public String getUserId(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(token).getBody();
		return claims.getSubject();		
	}
}
