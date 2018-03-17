package com.socialnet.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the friend database table.
 * 
 */
@Entity
@Table(name="friend")
public class Friend implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FriendPK id;

	private int status;

    public Friend() {
    }

	public FriendPK getId() {
		return this.id;
	}

	public void setId(FriendPK id) {
		this.id = id;
	}
	
	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}