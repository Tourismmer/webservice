package com.tourismmer.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tourismmer.app.constants.ViewConstants;

@Entity
@Table (name = "tp_type_post")
public class TypePost extends Model {
	
	@Id
	@GeneratedValue
	@Column(name = "tp_id")
	private Long id = null;
	
	@Column(name = "tp_description")
	private String description = ViewConstants.EMPYT;
	
	public TypePost() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
