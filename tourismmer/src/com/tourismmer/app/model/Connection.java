package com.tourismmer.app.model;

import java.util.ArrayList;
import java.util.List;

public class Connection {
	
    private List<Msg> messages;

    private User sender;

    private List<User> receivers = new ArrayList<User>();

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

	public List<User> getReceivers() {
		return receivers;
	}

	public void setReceivers(List<User> receivers) {
		this.receivers = receivers;
	}

}
