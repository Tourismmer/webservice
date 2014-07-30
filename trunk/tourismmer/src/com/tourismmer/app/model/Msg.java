package com.tourismmer.app.model;

import java.util.Date;

import com.tourismmer.app.util.Util;

public class Msg {
	
    private Long id;

    private Long chatId;

    private String text;

    private User sender = new User();

    private Date date = new Date();
    
    public Msg() {
    }

    public String DatetimeFormatted() {
    	return Util.formatDate(date);
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getChatId() {
		return chatId;
	}

	public void setChatId(Long chatId) {
		this.chatId = chatId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
