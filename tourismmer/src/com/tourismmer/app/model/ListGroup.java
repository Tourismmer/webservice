package com.tourismmer.app.model;

import java.util.ArrayList;
import java.util.List;

import com.tourismmer.app.constants.Numeros;

public class ListGroup extends Model {
	
	private List<Group> listGroup = null;
	
	private Boolean hasMoreData = Boolean.FALSE;
	
	public ListGroup() {
		listGroup = new ArrayList<Group>(Numeros.ZERO);
	}

	public List<Group> getListGroup() {
		return listGroup;
	}

	public void setListGroup(List<Group> listGroup) {
		this.listGroup = listGroup;
	}

	public Boolean getHasMoreData() {
		return hasMoreData;
	}

	public void setHasMoreData(Boolean hasMoreData) {
		this.hasMoreData = hasMoreData;
	}
	
}
