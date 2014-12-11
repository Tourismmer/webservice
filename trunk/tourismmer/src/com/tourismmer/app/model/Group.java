package com.tourismmer.app.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.tourismmer.app.constants.ViewConstants;
import com.tourismmer.app.json.PurposeDeserializer;
import com.tourismmer.app.json.PurposeSerializer;

@Entity
@Table (name = "trip")
public class Group extends Model {
	
	@Id
	@GeneratedValue
	@Column(name = "tr_id")
	private Long id = null;
	
	@Column(name = "tr_destination")
	private String destination = ViewConstants.EMPYT;
	
	@OneToOne
	@JoinColumn(name = "tr_pu_id")
	@Enumerated(EnumType.STRING)
	@JsonDeserialize(using=PurposeDeserializer.class)
	@JsonSerialize(using=PurposeSerializer.class)
	private Purpose purpose;
	
	@ManyToOne(optional=false)
	@JoinColumn(name = "tr_us_id_owner")
	private User user = new User();
	
	@ManyToMany
	@JoinTable( name = "trip_user", 
		joinColumns = @JoinColumn(name = "tu_tr_id"), inverseJoinColumns = @JoinColumn(name = "tu_us_id") )
	private Collection<User> userList;
	
	@OneToOne
	@JoinColumn(name = "tr_im_id")
	private Image image;

	public Group() {
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Purpose getPurpose() {
		return purpose;
	}

	public void setPurpose(Purpose purpose) {
		this.purpose = purpose;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

}
