package com.mongo.posts.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mongo.posts.models.dto.PostDTO;
import com.mongo.posts.models.dto.UserDTO;
import com.mongo.posts.services.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService service;
	
	@ApiOperation(value="Lista os usuários")
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll(){
		List<UserDTO> entity = service.findAll();
		return ResponseEntity.ok().body(entity);
	}
	
	@ApiOperation(value="Busca por id")
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id){
		UserDTO entity = service.findById(id);
		return ResponseEntity.ok().body(entity);			
	}
	
	@ApiOperation(value="Insere novo usuário")
	@PostMapping
	public ResponseEntity<UserDTO> insert(@RequestBody UserDTO dto){
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId())
				.toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@ApiOperation(value="Atualiza usuário")
	@PutMapping(value = "/{id}")
	public ResponseEntity<UserDTO> update(@PathVariable String id, @RequestBody UserDTO dto){
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}

	@ApiOperation(value="Deleta usuário")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<UserDTO> delete(@PathVariable String id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value="Lista os posts do usuário")
	@GetMapping(value = "/{id}/posts")
	public ResponseEntity<List<PostDTO>> getUserPosts(@PathVariable String id){
		List<PostDTO> entity = service.getUserPosts(id);
		return ResponseEntity.ok().body(entity);			
	}
}
