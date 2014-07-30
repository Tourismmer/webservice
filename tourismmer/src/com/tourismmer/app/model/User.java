package com.tourismmer.app.model;

import java.util.Date;
import java.util.List;

import com.tourismmer.app.util.Util;

public class User {
	
	private Long id;

    private Long fbID;

    private int type;

    private String name;

    private String email;

    private String password;

    private Long numberJoins;

    private String level;

    private String address;
    
    private List<Trip> luggage;
    
    private Long situation;

    private Date birthDate;
    
    private String location;

    private String gender;

    private String relStatus;

    private String latitute;

    private String longitute;
    
    private List<Chat> chats;
    
    public User() {
    }
    
	public Trip lateTrip() {
		if (luggage == null || luggage.size() == 0)
          {
              return null;
          }
          else
          {
              return luggage.get(0);
          }
	}
	
	public String getBirthDateFormatate() {
		return Util.formatDate(birthDate);
	}
	
	public String getUrlIdEncrypted() {
		 return "/Profile?profile=" /* + Util.Encrypt(id.toString(), Constants.ENCRYPT_KEY) */;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getFbID() {
		return fbID;
	}

	public void setFbID(Long fbID) {
		this.fbID = fbID;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getNumberJoins() {
		return numberJoins;
	}

	public void setNumberJoins(Long numberJoins) {
		this.numberJoins = numberJoins;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Trip> getLuggage() {
		return luggage;
	}

	public void setLuggage(List<Trip> luggage) {
		this.luggage = luggage;
	}

	public Long getSituation() {
		return situation;
	}

	public void setSituation(Long situation) {
		this.situation = situation;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getRelStatus() {
		return relStatus;
	}

	public void setRelStatus(String relStatus) {
		this.relStatus = relStatus;
	}

	public String getLatitute() {
		return latitute;
	}

	public void setLatitute(String latitute) {
		this.latitute = latitute;
	}

	public String getLongitute() {
		return longitute;
	}

	public void setLongitute(String longitute) {
		this.longitute = longitute;
	}

	public List<Chat> getChats() {
		return chats;
	}

	public void setChats(List<Chat> chats) {
		this.chats = chats;
	}

    
}
