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
import com.tourismmer.app.json.PostSerializer;

@Entity
@Table(name = "po_post")
@JsonSerialize(using=PostSerializer.class)
public class Post extends Model {
	
	@Id
	@GeneratedValue
	@Column(name = "po_id")
	private Long id = null;
	
	@Column(name = "po_description")
	private String description = ViewConstants.EMPYT;
	
	@ManyToOne(optional=false, fetch=FetchType.LAZY)
	@JoinColumn(name = "po_tr_id_trip")
	private Group group = new Group();
	
	@ManyToOne(optional=false)
	@JoinColumn(name = "po_us_id_author")
	private User author = new User();
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable( name = "ug_user_go", 
		joinColumns = @JoinColumn(name = "ug_po_id_post"), inverseJoinColumns = @JoinColumn(name = "ug_us_id_user") )
	private Collection<User> userList = new ArrayList <User>();
	
	@ManyToOne(optional=false)
	@JoinColumn(name = "po_im_id_image")
	private Image image;
	
	@ManyToOne(optional=false)
	@JoinColumn(name = "po_tp_id_type_post")
	private TypePost typePost;
	
	@Transient
	private Integer countComment = null;
	
	@Transient
	private Integer countUserGo = null;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "po_date")
	@JsonDeserialize(using=CalendarDeserializer.class)
	@JsonSerialize(using=CalendarSerializer.class)
	private Calendar date = null;
	
	public Post() {
		
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

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Collection<User> getUserList() {
		return userList;
	}

	public void setUserList(Collection<User> userList) {
		this.userList = userList;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public TypePost getTypePost() {
		return typePost;
	}

	public void setTypePost(TypePost typePost) {
		this.typePost = typePost;
	}

	public Integer getCountComment() {
		return countComment;
	}

	public void setCountComment(Integer countComment) {
		this.countComment = countComment;
	}

	public Integer getCountUserGo() {
		return countUserGo;
	}

	public void setCountUserGo(Integer countUserGo) {
		this.countUserGo = countUserGo;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

}
