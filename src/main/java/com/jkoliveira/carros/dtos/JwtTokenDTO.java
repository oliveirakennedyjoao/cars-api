package com.jkoliveira.carros.dtos;

public class JwtTokenDTO {
	String token;
	String authType;
	
	public JwtTokenDTO(String token, String authType) {
		super();
		this.token = token;
		this.authType = authType;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAuthType() {
		return authType;
	}

	public void setAuthType(String authType) {
		this.authType = authType;
	}
}
