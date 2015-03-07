package com.tourismmer.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "lp_like_post")
public class LikePost extends Model implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "lp_us_id_user")
	private Long idUser = null;
	
	@Id
	@Column(name = "lp_po_id_post")
	private Long idPost = null;
	

	public LikePost() {
	}


	public Long getIdUser() {
		return idUser;
	}


	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}


	public Long getIdPost() {
		return idPost;
	}


	public void setIdPost(Long idPost) {
		this.idPost = idPost;
	}

}