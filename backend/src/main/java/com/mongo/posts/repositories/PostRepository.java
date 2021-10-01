package com.mongo.posts.repositories;

import java.time.Instant;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.mongo.posts.models.entities.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

	List<Post> findByTitleContainingIgnoreCase(String title);
	//busca uma lista de titulos que contenha a palavra vindo como parametro ignorando o tamanho 
	
	@Query("{$and : [ { 'moment' : { $gte: ?1} }, { 'moment' : { $lte: ?2} },"
			+ " { $or: [ { 'title' : { $regex: ?0, $options: 'i'} },"
			+ " { 'body' : { $regex: ?0, $options: 'i'} }," 
			+ "	{ 'comments.text' : { $regex: ?0, $options: 'i'} } ]}]}")
	List<Post> fullSearch(String text, Instant startMoment, Instant endMoment);
	
	
}
