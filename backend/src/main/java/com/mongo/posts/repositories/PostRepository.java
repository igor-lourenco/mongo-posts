package com.mongo.posts.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mongo.posts.models.entities.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

	List<Post> findByTitleContainingIgnoreCase(String title);
	//busca uma lista de titulos que contenha a palavra vindo como parametro ignorando o tamanho 
}
