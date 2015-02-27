package com.tourismmer.app.model;

import java.util.ArrayList;
import java.util.List;

import com.tourismmer.app.constants.Numeros;

public class ListPost extends Model {
	
	private List<Post> listPost = null;
	
	public ListPost() {
		listPost = new ArrayList<Post>(Numeros.ZERO);
	}

	public List<Post> getListPost() {
		return listPost;
	}

	public void setListPost(List<Post> listPost) {
		this.listPost = listPost;
	}
	
}
