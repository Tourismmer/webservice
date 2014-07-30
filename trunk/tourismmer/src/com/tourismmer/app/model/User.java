package com.tourismmer.app.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.tourismmer.app.util.ViewConstants;

@Entity
@Table
public class User extends Model {
	
	@Id
	@GeneratedValue
	@Column(name = "us_id")
	public Long id = null;
	
	@Column(name = "us_name")
	public String name = ViewConstants.VAZIO;
	
	@Column(name = "us_city")
	public String city = ViewConstants.VAZIO;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "us_birthday")
	private Calendar birthday = null;
	
	@Column(name = "us_email")
	public String email = ViewConstants.VAZIO;
	
	@Column(name = "us_pass")
	public String pass = ViewConstants.VAZIO;
	
	@Column(name = "us_gender")
	public String gender = ViewConstants.VAZIO;
	
	@Column(name = "us_relationship_status")
	public String relationshipStatus = ViewConstants.VAZIO;
	
	@Column(name = "us_facebook_id")
	public String facebookId = ViewConstants.VAZIO;
	
    public User() {
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Calendar getBirthday() {
		return birthday;
	}

	public void setBirthday(Calendar birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getRelationshipStatus() {
		return relationshipStatus;
	}

	public void setRelationshipStatus(String relationshipStatus) {
		this.relationshipStatus = relationshipStatus;
	}

	public String getFacebookId() {
		return facebookId;
	}

	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
    
}
