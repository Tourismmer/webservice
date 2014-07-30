package com.tourismmer.app.model;

import java.util.List;

public class Feed {
	
    private Long id;

    private List<Msg> messages;

    private String location = "";

    private String a;

    private String k;
    
    public Feed(){
    }
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Msg> getMessages() {
		return messages;
	}

	public void setMessages(List<Msg> messages) {
		this.messages = messages;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getK() {
		return k;
	}

	public void setK(String k) {
		this.k = k;
	}

}
