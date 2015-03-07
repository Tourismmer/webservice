package com.tourismmer.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
	
	@OneToOne
	@JoinColumn(name = "im_us_id_owner")
	private User owner;
	
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

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

}
