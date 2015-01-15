package com.tourismmer.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tourismmer.app.constants.ViewConstants;

@Entity
@Table (name = "im_image")
public class Image extends Model {
	
	@Id
	@GeneratedValue
	@Column(name = "im_id")
	private Long id = null;
	
	@Column(name = "im_url")
	private String url = ViewConstants.EMPYT;
	
	public Image() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
