package com.socialnet.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the scrap_content database table.
 * 
 */
@Entity
@Table(name="scrap_content")
public class ScrapContent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String scrapid;

	private String content;

    public ScrapContent() {
    }

	public String getScrapid() {
		return this.scrapid;
	}

	public void setScrapid(String scrapid) {
		this.scrapid = scrapid;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}