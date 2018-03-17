package com.socialnet.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the friend database table.
 * 
 */
@Embeddable
public class FriendPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String emailid;

	private String friendid;

    public FriendPK() {
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

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FriendPK)) {
			return false;
		}
		FriendPK castOther = (FriendPK)other;
		return 
			this.emailid.equals(castOther.emailid)
			&& this.friendid.equals(castOther.friendid);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.emailid.hashCode();
		hash = hash * prime + this.friendid.hashCode();
		
		return hash;
    }
}