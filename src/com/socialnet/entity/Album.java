package com.socialnet.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the album database table.
 * 
 */
@Entity
@Table(name="album")
public class Album implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String albumid;

	@Column(name="album_name")
	private String albumName;

    @Temporal( TemporalType.DATE)
	@Column(name="date_of_creation")
	private Date dateOfCreation;

	private String emailid;

    public Album() {
    }

	public String getAlbumid() {
		return this.albumid;
	}

	public void setAlbumid(String albumid) {
		this.albumid = albumid;
	}

	public String getAlbumName() {
		return this.albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public Date getDateOfCreation() {
		return this.dateOfCreation;
	}

	public void setDateOfCreation(Date dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}

	public String getEmailid() {
		return this.emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

}