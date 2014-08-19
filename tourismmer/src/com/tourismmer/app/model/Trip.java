package com.tourismmer.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.tourismmer.app.constants.Constants;

@Entity
public class Trip extends Model {
	
	@Id
	@GeneratedValue
	@Column(name = "tr_id")
	public Long id = null;
	
	@Column(name = "tr_destination")
	private String destination = Constants.EMPYT;
	
	@Column(name = "tr_purpose")
	private String purpose = Constants.EMPYT;
	
	@ManyToOne(optional=false)
	@JoinColumn(name = "tr_us_id")
	private User user = new User();
	
	public Trip() {
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

}
