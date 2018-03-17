package com.socialnet.beans;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.socialnet.entity.Profile;
import com.socialnet.entity.Registration;
import com.socialnet.util.DataProvider;

public class ForgotPasswordBean {

	
	private String password;
	private String secqus;
	private String secans;
	private String confpasswd;
	private String email;
	private String message;
	private boolean ansMatched;
	
	
	public ForgotPasswordBean(){
		 HttpSession hs=(HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		 RegistrationBean rb=(RegistrationBean)hs.getAttribute("registrationBean");
		
		 
		 EntityManager em=DataProvider.getEntityManager();
		 Registration r =em.find(Registration.class, rb.getRegistration().getEmailid());
		 
		 if(r==null){
			 setMessage("Invalid emailid...");
			 return;
		 }
		 
		 setSecqus(r.getSecurityQuestion());
		 setEmail(r.getEmailid());
	}
	
	public String newPassword(String passwd)
	{
		EntityManager em=DataProvider.getEntityManager();
		System.out.println("Saving details to database.....");
		em.getTransaction().begin();		
		em.getTransaction().commit();
		return("newpass");
	}
	
	
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
	public void setSecqus(String secqus) {
		this.secqus = secqus;
	}
	public String getSecqus() {
		return secqus;
	}
	public void setSecans(String secans) {
		this.secans = secans;
	}
	public String getSecans() {
		return secans;
	}
	public void setConfpasswd(String confpasswd) {
		this.confpasswd = confpasswd;
	}
	public String getConfpasswd() {
		return confpasswd;
	}
	
  public void ansMatch(ActionEvent e)
  {
	 EntityManager em=DataProvider.getEntityManager();
	 Query q=em.createQuery("Select count(r) from Registration r where r.emailid=:emailid and r.securityQuestion=:secqsn and r.securityAnswer=:secans");
	 q.setParameter("emailid", getEmail());
	 q.setParameter("secqsn",getSecqus());
	 q.setParameter("secans", getSecans());
	 long count=(Long) q.getSingleResult();
	 setAnsMatched(count>0);
  }
public void setEmail(String email) {
	this.email = email;
}
public String getEmail() {
	return email;
}

public void setMessage(String message) {
	this.message = message;
}

public String getMessage() {
	return message;
}

public void setAnsMatched(boolean ansMatched) {
	this.ansMatched = ansMatched;
}

public boolean isAnsMatched() {
	return ansMatched;
}

}
