package com.tourismmer.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tourismmer.app.constants.ViewConstants;

@Entity
@Table (name = "ig_image_s3")
public class ImageS3 extends Model {
	
	@Id
	@GeneratedValue
	@Column(name = "ig_id")
	private Long id = null;
	
	@Column(name = "ig_sequence")
	private String sequence = ViewConstants.EMPYT;
	
	public ImageS3() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
}
