package com.tourismmer.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "gu_group_user")
public class GroupUser extends Model implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "gu_gr_id_group")
	private Long idGroup = null;
	
	@Id
	@Column(name = "gu_us_id_user")
	private Long idUser = null;
	

	public GroupUser() {
	}


	public Long getIdGroup() {
		return idGroup;
	}


	public void setIdGroup(Long idGroup) {
		this.idGroup = idGroup;
	}


	public Long getIdUser() {
		return idUser;
	}


	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	

}
