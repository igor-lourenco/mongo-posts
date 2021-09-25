package com.mongo.posts.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongo.posts.models.dto.UserDTO;
import com.mongo.posts.models.entities.User;
import com.mongo.posts.repositories.UserRepository;
import com.mongo.posts.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public List<UserDTO> findAll(){
		List<User> entity = repository.findAll();
		return entity.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
	}
	
	public UserDTO findById(String id) {
		Optional<User> obj = repository.findById(id);
		User entity = obj.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado -> " + id));
		return new UserDTO(entity);
	}
}
