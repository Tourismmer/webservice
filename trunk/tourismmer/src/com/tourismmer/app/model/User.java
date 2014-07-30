package com.tourismmer.app.model;

import java.util.Date;

import com.tourismmer.app.util.ViewConstants;


public class User extends Model {
	
	public String name = ViewConstants.VAZIO;
	
	public String city = ViewConstants.VAZIO;
	
	private Date birthday = null;
	
	public String email = ViewConstants.VAZIO;
	
	public String pass = ViewConstants.VAZIO;
	
	public String gender = ViewConstants.VAZIO;
	
	public String relationshipStatus = ViewConstants.VAZIO;
	
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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
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
    
}
