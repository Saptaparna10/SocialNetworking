package com.socialnet.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the post_comment database table.
 * 
 */
@Entity
@Table(name="post_comment")
public class PostComment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="post_commentid")
	private String postCommentid;

	private String friendid;

	@Column(name="post_comments")
	private String postComments;

	private String postid;

    public PostComment() {
    }

	public String getPostCommentid() {
		return this.postCommentid;
	}

	public void setPostCommentid(String postCommentid) {
		this.postCommentid = postCommentid;
	}

	public String getFriendid() {
		return this.friendid;
	}

	public void setFriendid(String friendid) {
		this.friendid = friendid;
	}

	public String getPostComments() {
		return this.postComments;
	}

	public void setPostComments(String postComments) {
		this.postComments = postComments;
	}

	public String getPostid() {
		return this.postid;
	}

	public void setPostid(String postid) {
		this.postid = postid;
	}

}