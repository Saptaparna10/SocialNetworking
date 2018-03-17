package com.socialnet.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the likes database table.
 * 
 */
@Entity
@Table(name="like")
public class Like implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String likeid;

	private String friendid;

	private String picid;

    public Like() {
    }

	public String getLikeid() {
		return this.likeid;
	}

	public void setLikeid(String likeid) {
		this.likeid = likeid;
	}

	public String getFriendid() {
		return this.friendid;
	}

	public void setFriendid(String friendid) {
		this.friendid = friendid;
	}

	public String getPicid() {
		return this.picid;
	}

	public void setPicid(String picid) {
		this.picid = picid;
	}

}