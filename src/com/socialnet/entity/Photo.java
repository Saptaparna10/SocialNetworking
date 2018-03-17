package com.socialnet.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the photo database table.
 * 
 */
@Entity
@Table(name="photo")
public class Photo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String picid;

	private int albumid;

	private String caption;

	private String photoname;

	private int rating;

    public Photo() {
    }

	public String getPicid() {
		return this.picid;
	}

	public void setPicid(String picid) {
		this.picid = picid;
	}

	public int getAlbumid() {
		return this.albumid;
	}

	public void setAlbumid(int albumid) {
		this.albumid = albumid;
	}

	public String getCaption() {
		return this.caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getPhotoname() {
		return this.photoname;
	}

	public void setPhotoname(String photoname) {
		this.photoname = photoname;
	}

	public int getRating() {
		return this.rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

}