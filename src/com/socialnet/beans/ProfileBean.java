package com.socialnet.beans;

import java.io.IOException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.socialnet.entity.*;

import com.socialnet.util.DataProvider;

public class ProfileBean {
	private Profile profile;
	private Registration registration;
	
	
	public ProfileBean(){
		HttpSession hs=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		String logId=(String)hs.getAttribute("logid");
		
		System.out.println("User ID: "+logId);
		
		String friendId=(String)hs.getAttribute("friendId");
			System.out.println("Friend ID: "+friendId);
		
		if(friendId!=null){
				logId=friendId;
			 //hs.removeAttribute("friendId");
		}
			
		String sql="Select p from Profile p where p.emailid=:emailid";
		EntityManager em=DataProvider.getEntityManager();
		Query q=em.createQuery(sql);			
		q.setParameter("emailid",logId);
		setProfile((Profile)q.getSingleResult());
		
		sql="Select r from Registration r where r.emailid=:emailid";
		q=em.createQuery(sql);
		q.setParameter("emailid",logId);		
		setRegistration((Registration)q.getSingleResult());
		System.out.println(getRegistration().getDob());
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setRegistration(Registration registration) {
		this.registration = registration;
	}

	public Registration getRegistration() {
		return registration;
	}
	
	
/*	public void showUpdateBox(ActionEvent e)
	{
		setUpdateBox(true);
		setViewBox(false);
		System.out.print("hmm");
	}*/
	
	public void saveDetails(ActionEvent e){
		EntityManager em=DataProvider.getEntityManager();
		System.out.println("Saving details to database.....");
		em.getTransaction().begin();
		
		em.merge(getRegistration());
		em.merge(getProfile());
		
		em.getTransaction().commit();
		
		HttpServletResponse hr=(HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		try {
			hr.sendRedirect("myprofile.xhtml");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	public String logOut(){
		ExternalContext exCntx=FacesContext.getCurrentInstance().getExternalContext();
		HttpSession hs=(HttpSession) exCntx.getSession(false);
		hs.invalidate();
//		hs.removeAttribute("logid");
//		hs.removeAttribute("logname");
//		hs.removeAttribute("searchFriendBean");
		
		HttpServletResponse response=(HttpServletResponse) exCntx.getResponse();
		
		response.setHeader( "Pragma", "no-cache" );
		response.setHeader( "Cache-Control", "no-cache" );
		response.setHeader( "Expires","Sat, 01 Dec 2001 00:00:00 GMT");
		
		return "logout";
	}
	
	public String showMyProfile(){
		ExternalContext exCntx=FacesContext.getCurrentInstance().getExternalContext();
		HttpSession hs=(HttpSession) exCntx.getSession(false);
		hs.removeAttribute("friendId");
		return "myprofile";
	}
	
	public String showMyHome(){
		ExternalContext exCntx=FacesContext.getCurrentInstance().getExternalContext();
		HttpSession hs=(HttpSession) exCntx.getSession(false);
		hs.removeAttribute("friendId");
		return "home";
	}
}
