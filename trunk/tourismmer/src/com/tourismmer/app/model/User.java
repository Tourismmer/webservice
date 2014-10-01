package com.tourismmer.app.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.tourismmer.app.constants.Constants;
import com.tourismmer.app.json.CalendarDeserializer;
import com.tourismmer.app.json.CalendarSerializer;

@Entity
public class User extends Model {
	
	@Id
	@GeneratedValue
	@Column(name = "us_id")
	public Long id = null;
	
	@Column(name = "us_name")
	public String name = Constants.EMPYT;
	
	@Column(name = "us_city")
	public String city = Constants.EMPYT;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "us_birthday")
	@JsonDeserialize(using=CalendarDeserializer.class)
	@JsonSerialize(using=CalendarSerializer.class)
	private Calendar birthday = null;
	
	@Column(name = "us_email")
	public String email = Constants.EMPYT;
	
	@Column(name = "us_pass")
	public String pass = Constants.EMPYT;
	
	@Column(name = "us_gender")
	public String gender = Constants.EMPYT;
	
	@Column(name = "us_relationship_status")
	public String relationshipStatus = Constants.EMPYT;
	
	@Column(name = "us_facebook_id")
	public String facebookId = Constants.EMPYT;
	
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
