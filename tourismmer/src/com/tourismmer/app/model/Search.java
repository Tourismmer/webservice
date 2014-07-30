package com.tourismmer.app.model;

import java.util.List;

public class Search {
	
    private Feed feed;

    private Trip trip;

    private User user;

    private List<User> users;

    private Boolean isPublished;

    private Boolean isUserRegistered;
    
    public Search() {
    }
    
	public Feed getFeed() {
		return feed;
	}

	public void setFeed(Feed feed) {
		this.feed = feed;
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Boolean getIsPublished() {
		return isPublished;
	}

	public void setIsPublished(Boolean isPublished) {
		this.isPublished = isPublished;
	}

	public Boolean getIsUserRegistered() {
		return isUserRegistered;
	}

	public void setIsUserRegistered(Boolean isUserRegistered) {
		this.isUserRegistered = isUserRegistered;
	}

}
