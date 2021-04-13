package com.jkoliveira.carros.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jkoliveira.carros.dtos.UserDTO;
import com.jkoliveira.carros.models.User;
import com.jkoliveira.carros.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<UserDTO> list(){
		
		List<UserDTO> usersDTO = new ArrayList<>();		
		userRepository.findAll().forEach((user) -> { usersDTO.add(user.toDTO()); });		
		return usersDTO;
	}
		
	@Transactional
	public void save(UserDTO userDTO) {
		User newUser = userDTO.toEntity();
		userRepository.save(newUser);
	}
		
	public UserDTO get(String login) {		
		return userRepository.findById(login).get().toDTO();
	}
	
	@Transactional
	public UserDTO update(String login, UserDTO userDTO) {
		Optional<User> userToUpdate = userRepository.findById(login);
		if(userToUpdate.isPresent()) {
			userToUpdate.get().setFirstName(userDTO.getFirstName());
			userToUpdate.get().setLastName(userDTO.getLastName());
			userToUpdate.get().setEmail(userDTO.getEmail());
			userToUpdate.get().setBirthday(userDTO.getBirthday());
			userToUpdate.get().setLogin(userDTO.getLogin());
			userToUpdate.get().setPhone(userDTO.getPhone());
		}
		return userToUpdate.get().toDTO();
	}
	
	@Transactional
	public void delete(String login) {
		userRepository.deleteById(login);		
	}
}
