package com.tourismmer.app.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tourismmer.app.constants.Constants;

@Entity
@Table (name = "group")
public class Group extends Model {
	
	@Id
	@GeneratedValue
	@Column(name = "gr_id")
	private Long id = null;
	
	@Column(name = "gr_destination")
	private String destination = Constants.EMPYT;
	
	@Column(name = "gr_purpose")
	private String purpose = Constants.EMPYT;
	
	@ManyToOne(optional=false)
	@JoinColumn(name = "gr_us_id_owner")
	private User user = new User();
	
	@ManyToMany
	@JoinTable( name = "group_user", 
		joinColumns = @JoinColumn(name = "gu_gr_id"), inverseJoinColumns = @JoinColumn(name = "gu_us_id") )
	private Collection<User> userList;

	public Group() {
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
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

}
