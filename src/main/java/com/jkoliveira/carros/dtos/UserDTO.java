package com.jkoliveira.carros.dtos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.jkoliveira.carros.models.Car;
import com.jkoliveira.carros.models.Profile;
import com.jkoliveira.carros.models.User;

public class UserDTO {
	private static final long serialVersionUID = 4392901932696540398L;
	
	private String firstName;
	private String lastName;
	private String email;
	private LocalDate birthday;	
	private String login;
	private String password;
	private String phone;

	public UserDTO(String firstName, String lastName, String email, LocalDate birthday, String login, String password,
			String phone) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.birthday = birthday;
		this.login = login;
		this.password = password;
		this.phone = phone;
	}
	
	public User toEntity() {
		return new User(firstName, lastName, email, birthday, login, password, phone);
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public LocalDate getBirthday() {
		return birthday;
	}
	
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
