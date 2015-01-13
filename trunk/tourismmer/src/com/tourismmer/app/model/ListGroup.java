package com.tourismmer.app.model;

import java.util.ArrayList;
import java.util.List;

import com.tourismmer.app.constants.Numeros;

public class ListGroup extends Model {
	
	List<Group> listGroup = null;
	
	public ListGroup() {
		listGroup = new ArrayList<Group>(Numeros.ZERO);
	}

	public List<Group> getListGroup() {
		return listGroup;
	}

	public void setListGroup(List<Group> listGroup) {
		this.listGroup = listGroup;
	}
	
}
