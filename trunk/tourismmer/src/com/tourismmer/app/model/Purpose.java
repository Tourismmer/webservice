package com.tourismmer.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "purpose")
public enum Purpose {
	
	TRAVEL (1,"Travel"),
	VACATION (3,"Vacation"),;
	
	@Id
	@GeneratedValue
	@Column(name = "pu_id")
	private final Integer id;

	@Column(name = "pu_description")
	private final String description;
	
	Purpose(Integer idParam, String descriptionParam) {
		this.id = idParam;
		this.description = descriptionParam;
	}

	public Integer getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}
	
	public static Purpose getPurpose(Integer id) {
		
		if(Purpose.TRAVEL.getId().equals(id)) {
			return Purpose.TRAVEL;
			
		} else if(Purpose.VACATION.getId().equals(id)) {
			return Purpose.VACATION;
		}
		
		return null;
	}

}
