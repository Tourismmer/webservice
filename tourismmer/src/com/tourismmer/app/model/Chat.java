package com.tourismmer.app.model;

import java.util.ArrayList;
import java.util.List;

public class Chat {
	
	private Long id;

    private List<Msg> messages = new ArrayList<>();

    private User sender;

    private User receiver = new User();
    
    public Chat() {
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

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

}
