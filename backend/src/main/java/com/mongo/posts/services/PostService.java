package com.mongo.posts.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongo.posts.models.dto.PostDTO;
import com.mongo.posts.models.entities.Post;
import com.mongo.posts.repositories.PostRepository;
import com.mongo.posts.services.exceptions.ResourceNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repository;
	
	
	public PostDTO findById(String id) {
		Post entity = getEntityById(id);
		return new PostDTO(entity);
	}
	
	public List<PostDTO> findByTitle(String text){
		List<Post> lista = repository.findByTitleContainingIgnoreCase(text);
		return lista.stream().map(x -> new PostDTO(x)).collect(Collectors.toList());
	}
	
	
	private Post getEntityById(String id) {
		Optional<Post> obj = repository.findById(id);
		return  obj.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado -> " + id));
	}
}
