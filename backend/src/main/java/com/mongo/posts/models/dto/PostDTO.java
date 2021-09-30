package com.mongo.posts.models.dto;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.mongo.posts.models.embedded.Author;
import com.mongo.posts.models.embedded.Comment;
import com.mongo.posts.models.entities.Post;

public class PostDTO {

	private String id;

	private Instant moment;
	private String title;
	private String body;
	
	private Author author;
	private Set<Comment> comments = new HashSet<>();
	
	public PostDTO() {
		
	}

	public PostDTO(String id, Instant moment, String title, String body, Author author) {
		this.id = id;
		this.moment = moment;
		this.title = title;
		this.body = body;
		this.author = author;
	}
	
	public PostDTO(Post entity) {
		this.id = entity.getId(); 
		this.moment = entity.getMoment();
		this.title = entity.getTitle();
		this.body = entity.getBody();
		this.author = entity.getAuthor();
		this.comments.addAll(entity.getComments());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Set<Comment> getComments() {
		return comments;
	}
}
