package com.jkoliveira.carros.dtos;

public class JwtTokenDTO {
	
	UserLoginNameDTO authenticatedUserDTO;
	String token;
	String authType;
	
	public JwtTokenDTO(String token, String authType, 
			UserLoginNameDTO authenticatedUserDTO) {
		super();
		this.authenticatedUserDTO = authenticatedUserDTO;
		this.token = token;
		this.authType = authType;
	}

	public UserLoginNameDTO getAuthenticatedUserDTO() {
		return authenticatedUserDTO;
	}

	public void setAuthenticatedUserDTO(UserLoginNameDTO authenticatedUserDTO) {
		this.authenticatedUserDTO = authenticatedUserDTO;
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
