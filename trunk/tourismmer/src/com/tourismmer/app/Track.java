package com.tourismmer.app;

import java.util.List;

public class Track {

	String title;
	String singer;
	
	List<Track> list;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	@Override
	public String toString() {
		return "Track [title=" + title + ", singer=" + singer + "]";
	}

	public List<Track> getList() {
		return list;
	}

	public void setList(List<Track> list) {
		this.list = list;
	}

}
