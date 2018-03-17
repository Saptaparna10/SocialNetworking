package com.socialnet.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.*;
import javax.servlet.http.HttpSession;

import com.socialnet.entity.Profile;
import com.socialnet.entity.Registration;
import com.socialnet.util.DataProvider;

public class RegistrationBean {
	private Registration registration;
	
	private EntityManager em;
	private String message;
	private boolean invalidUser;

	public List<Registration> getAllUsers(){
		em=DataProvider.getEntityManager();
		return em.createQuery("Select r from Registration r").getResultList();
	}
	public List <Registration> suggest (String input){ 
		   List <Registration> result = new ArrayList <Registration>(); 
		   for(Registration reg : getAllUsers()) { 
		      if ((reg.getName().toLowerCase()).startsWith(input.toLowerCase())) 
		         result.add(reg); 
		      } 
		   return result; 
	} 
		
	
	public RegistrationBean(){
		setRegistration(new Registration());
	    
		em=DataProvider.getEntityManager();
	}

	public void setRegistration(Registration registration) {
		this.registration = registration;
	}

	public Registration getRegistration() {
		return registration;
	}
	
	public String authenticate(){
		Query q=em.createQuery("Select r from Registration r where r.emailid=:emailid and r.password=:password");
		q.setParameter("emailid", registration.getEmailid());
		q.setParameter("password", registration.getPassword());
		if(q.getResultList().size()>0){
			registration=(Registration)q.getSingleResult();
			HttpSession hs=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
			hs.setAttribute("logid", registration.getEmailid());
			hs.setAttribute("logname", registration.getName());
			
			return "valid";
		}	
		else{
			setMessage("Invalid userid/password");
			return "invalid";
		}
		    
	}
	

	private int getNextUserId(){
		int max=0;
		try{
			Query q=em.createNativeQuery("Select nullif(max(userid),1010) max from registration");
			max=(Integer) q.getSingleResult();
			
		}catch(Exception ex){
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(ex.getMessage()));
		}
		return max+1;
	}
	
	public void registerUser(ActionEvent e){
		try{
			Profile profile=new Profile();
			profile.setEmailid(registration.getEmailid());
			registration.setUserid(getNextUserId()+"");
			em.getTransaction().begin();
			em.persist(registration);
			em.persist(profile);
			em.getTransaction().commit();
		}catch(Exception ex){
			setMessage(ex.getMessage());
		}
		setMessage("Successfully registered...");
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	public void setInvalidUser(boolean invalidUser) {
		this.invalidUser = invalidUser;
	}

	public boolean getInvalidUser() {
		return invalidUser;
	}
	
	 public String getName(String emailId)
	  {
		  String sql="Select r from Registration r where r.emailid=:emailId";
		  EntityManager em=DataProvider.getEntityManager();
		  Query q=em.createQuery(sql);
		  q.setParameter("emailId", emailId);
		  Registration r=(Registration) q.getSingleResult();
		  
		  return(r.getName());
	  }
			
	
}
