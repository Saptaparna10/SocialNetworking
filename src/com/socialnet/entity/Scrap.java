package com.socialnet.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the scrap database table.
 * 
 */
@Entity
public class Scrap implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String scrapid;

    @Temporal( TemporalType.DATE)
	private Date date;

	private String emailid;

	private String friendid;

	@Column(name="is_viewed")
	private int isViewed;

	@Column(name="scrap_details")
	private String scrapDetails;

    public Scrap() {
    }

	public String getScrapid() {
		return this.scrapid;
	}

	public void setScrapid(String scrapid) {
		this.scrapid = scrapid;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getEmailid() {
		return this.emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getFriendid() {
		return this.friendid;
	}

	public void setFriendid(String friendid) {
		this.friendid = friendid;
	}

	public int getIsViewed() {
		return this.isViewed;
	}

	public void setIsViewed(int isViewed) {
		this.isViewed = isViewed;
	}

	public String getScrapDetails() {
		return this.scrapDetails;
	}

	public void setScrapDetails(String scrapDetails) {
		this.scrapDetails = scrapDetails;
	}

}