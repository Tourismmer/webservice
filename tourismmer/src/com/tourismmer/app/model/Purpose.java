package com.tourismmer.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "purpose")
public class Purpose {
	
	@Id
	@GeneratedValue
	@Column(name = "pu_id")
	private Integer id;

	@Column(name = "pu_description")
	private String description;
	
	public Purpose() {
	}
	
	public Purpose(Integer idParam, String descriptionParam) {
		this.id = idParam;
		this.description = descriptionParam;
	}
	
	public Integer getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
