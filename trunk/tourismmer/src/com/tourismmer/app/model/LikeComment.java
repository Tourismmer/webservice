package com.tourismmer.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "li_like_comment")
public class LikeComment extends Model implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "li_us_id_user")
	private Long idUser = null;
	
	@Id
	@Column(name = "li_co_id_comment")
	private Long idComment = null;
	

	public LikeComment() {
	}


	public Long getIdUser() {
		return idUser;
	}


	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}


	public Long getIdComment() {
		return idComment;
	}


	public void setIdComment(Long idComment) {
		this.idComment = idComment;
	}
	
}