package com.socialnet.beans;


import java.util.*;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import com.socialnet.entity.Scrap;
import com.socialnet.util.DataProvider;

public class ScrapBean {
	private Scrap scrap;
	private boolean showReply;
	
	public ScrapBean() {
		scrap=new Scrap();
	}
	
	
	public void postMsg(){
		
		System.out.println("posted..");
		HttpSession hs=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		 String logId=(String) hs.getAttribute("logid");
		 String friendId=(String) hs.getAttribute("friendId");
		 EntityManager em=DataProvider.getEntityManager();
	
		scrap.setEmailid(logId);
		scrap.setFriendid(friendId);
		//scrap.setIsviewed(0);
	//	scrap.setScrapDate(new Date());
		
		
		em.getTransaction().begin();
		em.persist(scrap);
		em.getTransaction().commit();
		hs.removeAttribute("scrapBean");
	}
	
	public List<Scrap> getScrapDetails()
	{
		HttpSession hs=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		String logid=(String) hs.getAttribute("logid");
		System.out.println(logid);
		EntityManager em=DataProvider.getEntityManager();
		Query q1=em.createQuery("Select s from Scrap s where s.friendid=:emailId");
		q1.setParameter("emailId", logid);
		
		return q1.getResultList();
	}
	
	public String removeScrap(String scrapId){
		EntityManager em=DataProvider.getEntityManager();
		Scrap s=em.find(Scrap.class, scrapId);
		
		em.getTransaction().begin();
		em.remove(s);
		em.getTransaction().commit();
		
		return null;
	}
	
	public void showReplyBox(ActionEvent e){
		setShowReply(true);
	}
	
	public void setScrap(Scrap scrap) {
		this.scrap = scrap;
	}
	
	public Scrap getScrap() {
		return scrap;
	}


	public void setShowReply(boolean showReply) {
		this.showReply = showReply;
	}


	public boolean isShowReply() {
		return showReply;
	}
	
public void rplyMsg(String friendId){
		
		System.out.println("posted..");
		HttpSession hs=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		 String logId=(String) hs.getAttribute("logid");		 
		 EntityManager em=DataProvider.getEntityManager();
	
		scrap.setEmailid(logId);
		scrap.setFriendid(friendId);
		//scrap.setIs_Viewed(0);
	//	scrap.setScrap_Date(new Date());
		
		
		em.getTransaction().begin();
		em.persist(scrap);
		em.getTransaction().commit();
		hs.removeAttribute("scrapBean");
	}
public String showMyMessage(){
	ExternalContext exCntx=FacesContext.getCurrentInstance().getExternalContext();
	HttpSession hs=(HttpSession) exCntx.getSession(false);
	hs.removeAttribute("friendId");
	return "message";
}
}
