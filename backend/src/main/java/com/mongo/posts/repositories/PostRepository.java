package com.mongo.posts.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mongo.posts.models.entities.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

}
