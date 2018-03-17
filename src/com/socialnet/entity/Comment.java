package com.socialnet.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the comment database table.
 * 
 */
@Entity
@Table(name="comment")
public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String commentid;

	private String comments;

	private String friendid;

	private String picid;

    public Comment() {
    }

	public String getCommentid() {
		return this.commentid;
	}

	public void setCommentid(String commentid) {
		this.commentid = commentid;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
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