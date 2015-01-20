package com.tourismmer.app.model;

import java.util.ArrayList;
import java.util.List;

import com.tourismmer.app.constants.Numeros;

public class ListComment extends Model {
	
	List<Comment> listComment = null;
	
	public ListComment() {
		listComment = new ArrayList<Comment>(Numeros.ZERO);
	}

	public List<Comment> getListComment() {
		return listComment;
	}

	public void setListComment(List<Comment> listComment) {
		this.listComment = listComment;
	}

}
