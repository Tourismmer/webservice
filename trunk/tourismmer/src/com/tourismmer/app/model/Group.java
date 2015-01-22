package com.tourismmer.app.model;

import java.util.ArrayList;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.tourismmer.app.constants.ViewConstants;

@Entity
@Table (name = "tr_trip")
public class Group extends Model {
	
	@Id
	@GeneratedValue
	@Column(name = "tr_id")
	private Long id = null;
	
	@Column(name = "tr_destination")
	private String destination = ViewConstants.EMPYT;
	
	@OneToOne
	@JoinColumn(name = "tr_pu_id_purpose")
	private Purpose purpose;
	
	@ManyToOne(optional=false)
	@JoinColumn(name = "tr_us_id_owner")
	private User owner = new User();
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable( name = "gt_group", 
		joinColumns = @JoinColumn(name = "gt_tr_id_trip"), inverseJoinColumns = @JoinColumn(name = "gt_us_id_user") )
	private Collection<User> userList = new ArrayList <User>();
	
	@ManyToOne(optional=false)
	@JoinColumn(name = "tr_im_id_image")
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

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

}
