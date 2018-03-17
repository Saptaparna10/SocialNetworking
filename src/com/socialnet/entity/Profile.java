package com.socialnet.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the profile database table.
 * 
 */
@Entity
@Table(name="profile")
public class Profile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String emailid;

	private String bio;

	private String college;

	private String dpname;

	@Column(name="number_of_albums")
	private int numberOfAlbums;

	@Column(name="number_of_friends")
	private int numberOfFriends;

	@Column(name="number_of_scraps")
	private int numberOfScraps;

	private String school;

	private String status;

	private String workplace;

    public Profile() {
    }

	public String getEmailid() {
		return this.emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getBio() {
		return this.bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getCollege() {
		return this.college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getDpname() {
		return this.dpname;
	}

	public void setDpname(String dpname) {
		this.dpname = dpname;
	}

	public int getNumberOfAlbums() {
		return this.numberOfAlbums;
	}

	public void setNumberOfAlbums(int numberOfAlbums) {
		this.numberOfAlbums = numberOfAlbums;
	}

	public int getNumberOfFriends() {
		return this.numberOfFriends;
	}

	public void setNumberOfFriends(int numberOfFriends) {
		this.numberOfFriends = numberOfFriends;
	}

	public int getNumberOfScraps() {
		return this.numberOfScraps;
	}

	public void setNumberOfScraps(int numberOfScraps) {
		this.numberOfScraps = numberOfScraps;
	}

	public String getSchool() {
		return this.school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getWorkplace() {
		return this.workplace;
	}

	public void setWorkplace(String workplace) {
		this.workplace = workplace;
	}

}