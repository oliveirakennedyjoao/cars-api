package com.jkoliveira.carros.models;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.jkoliveira.carros.dtos.UserDTO;

@Entity
public class User implements UserDetails {	
	
	private static final long serialVersionUID = 4392901932696540398L;
	
	private String firstName;
	private String lastName;
	private String email;
	private LocalDate birthday;  //(dd/MM/yyyy)	
	
	@Id
	private String login;
	private String password;
	private String phone;
	
	@ManyToMany
	private List<Car> cars;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Profile> profiles = new ArrayList<Profile>();
	
	public User() {
		super();
	}

	public User(String firstName, String lastName, String email, LocalDate birthday, String login, String password,
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		return true;
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
	
	@Override
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String senha) {
		this.password = senha;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public List<Car> getCars() {
		return cars;
	}
	
	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() { 
		return profiles;
	}

	@Override
	public String getUsername() {
		return firstName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {		
		return true;
	}

	@Override
	public boolean isEnabled() { 
		return true;
	}
	
	public UserDTO toDTO(){
		return new UserDTO(firstName, lastName, email, birthday, login, null, phone);
	}
}
