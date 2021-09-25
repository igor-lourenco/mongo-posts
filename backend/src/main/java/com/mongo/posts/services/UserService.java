package com.mongo.posts.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongo.posts.models.dto.UserDTO;
import com.mongo.posts.models.entities.User;
import com.mongo.posts.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public List<UserDTO> findAll(){
		List<User> entity = repository.findAll();
		return entity.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
	}
}
