package com.tourismmer.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tourismmer.app.constants.ViewConstants;

@Entity
@Table(name = "co_comment")
public class Comment extends Model {
	
	@Id
	@GeneratedValue
	@Column(name = "co_id")
	private Long id = null;
	
	@Column(name = "co_description")
	private String description = ViewConstants.EMPYT;
	
	@ManyToOne(optional=false)
	@JoinColumn(name = "co_po_id_post")
	private Post post = new Post();
	
	@ManyToOne(optional=false)
	@JoinColumn(name = "co_us_id_author")
	private User author = new User();
	
	public Comment() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

}
