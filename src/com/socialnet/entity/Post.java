package com.socialnet.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the post database table.
 * 
 */
@Entity
@Table(name="post")
public class Post implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String postid;

	private String emailid;

	private String posts;

    public Post() {
    }

	public String getPostid() {
		return this.postid;
	}

	public void setPostid(String postid) {
		this.postid = postid;
	}

	public String getEmailid() {
		return this.emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getPosts() {
		return this.posts;
	}

	public void setPosts(String posts) {
		this.posts = posts;
	}

}