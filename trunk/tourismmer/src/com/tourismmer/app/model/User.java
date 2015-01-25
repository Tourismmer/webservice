package com.tourismmer.app.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.tourismmer.app.constants.ViewConstants;
import com.tourismmer.app.json.CalendarDeserializer;
import com.tourismmer.app.json.CalendarSerializer;

@Entity
@Table(name = "us_user")
public class User extends Model {
	
	@Id
	@GeneratedValue
	@Column(name = "us_id")
	private Long id = null;
	
	@Column(name = "us_name")
	private String name = ViewConstants.EMPYT;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "us_birthday")
	@JsonDeserialize(using=CalendarDeserializer.class)
	@JsonSerialize(using=CalendarSerializer.class)
	private Calendar birthday = null;
	
	@Column(name = "us_email")
	private String email = ViewConstants.EMPYT;
	
	@Column(name = "us_pass")
//	@JsonIgnore
	private String pass = ViewConstants.EMPYT;
	
	@Column(name = "us_gender")
	private String gender = ViewConstants.EMPYT;
	
	@Column(name = "us_facebook_id")
	private String facebookId = ViewConstants.EMPYT;
	
    public User() {
    }
    
    public User(Long idParam) {
    	this.id = idParam;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

//	@JsonIgnore
	public String getPass() {
		return pass;
	}
	
//	@JsonProperty
	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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
