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
		User entity = getEntityById(id);
		return new UserDTO(entity);
	}
	
	public UserDTO insert(UserDTO dto) {
		User entity = new User();
		copyDTOToEntity(entity, dto);
		entity = repository.insert(entity);
		return new UserDTO(entity);
	}
	
	public UserDTO update(String id, UserDTO dto) {	
		User entity = getEntityById(id);
		copyDTOToEntity(entity, dto);
		entity= repository.save(entity);
		return new UserDTO(entity);
	}
	
	public void  delete(String id) {
		getEntityById(id);
		repository.deleteById(id);
	}
	
	private User getEntityById(String id) {
		Optional<User> obj = repository.findById(id);
		return  obj.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado -> " + id));
	}

	private void copyDTOToEntity(User entity, UserDTO dto) {	
		entity.setName(dto.getName());
		entity.setEmail(dto.getEmail());
	}
}
