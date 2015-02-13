package com.tourismmer.app.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.tourismmer.app.constants.ViewConstants;
import com.tourismmer.app.json.CalendarDeserializer;
import com.tourismmer.app.json.CalendarSerializer;
import com.tourismmer.app.json.CommentSerializer;

@Entity
@Table(name = "co_comment")
@JsonSerialize(using=CommentSerializer.class)
//@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer" })
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
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable( name = "li_like", 
		joinColumns = @JoinColumn(name = "li_us_id_user"), inverseJoinColumns = @JoinColumn(name = "li_co_id_comment") )
	private Collection<User> usersLike = new ArrayList <User>();
	
	@Transient
	private Integer countLike = null;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "co_date")
	@JsonDeserialize(using=CalendarDeserializer.class)
	@JsonSerialize(using=CalendarSerializer.class)
	private Calendar date = null;
	
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

	public Collection<User> getUsersLike() {
		return usersLike;
	}

	public void setUsersLike(Collection<User> usersLike) {
		this.usersLike = usersLike;
	}

	public Integer getCountLike() {
		return countLike;
	}

	public void setCountLike(Integer countLike) {
		this.countLike = countLike;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

}
