package com.socialnet.beans;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;

import com.socialnet.entity.Friend;
import com.socialnet.entity.FriendPK;
import com.socialnet.entity.Profile;
import com.socialnet.entity.Registration;
import com.socialnet.util.DataProvider;

public class FriendsBean {
     private String message;
    
     public FriendsBean() {
       
     }
     public String sendFriendRequest(String friendId,String fname) {
    	 HttpSession hs=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    	 hs.removeAttribute("friendId");
    	 
    	 String logId=(String)hs.getAttribute("logid");
    	
    	 Friend f=new Friend();
    	 f.setId(new FriendPK());
    	 f.getId().setEmailid(logId);
    	 f.getId().setFriendid(friendId);
    	 f.setStatus(0);
    	//// f.setGroup_Id("0");
    	 
    	 EntityManager em=DataProvider.getEntityManager();
    	 em.getTransaction().begin();
 		 em.persist(f);
 		 em.getTransaction().commit();
 		 
 		 setMessage("Friend request successfully send to "+fname);
 		 
 		 return null ;
     }
 public String acceptFriendRequest(String friendId) {
    	 HttpSession hs=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    	 String logId=(String)hs.getAttribute("logid");
    	 
    	 Friend f=new Friend();
    	 f.setId(new FriendPK());
    	 f.getId().setEmailid(logId);
    	 f.getId().setFriendid(friendId);
    	 f.setStatus(1);
    	// f.setGroupid(0);
    	 
    	 EntityManager em=DataProvider.getEntityManager();
    	 em.getTransaction().begin();
 		 em.persist(f);
 		 em.getTransaction().commit();
 		 
 		 //Update....
 		 f=new Friend();
	   	 f.setId(new FriendPK());
	   	 f.getId().setEmailid(friendId);
	   	 f.getId().setFriendid(logId);
	   	 f.setStatus(1);
	  // 	 f.setGroup_Id("0");
	   	 
	   	 em.getTransaction().begin();
		 em.merge(f);
		 em.getTransaction().commit();
 		 
 		 return null;
     }
 
	 public String rejectFriendRequest(String friendId) {	
		 
		 
		 HttpSession hs=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		 String logId=(String)hs.getAttribute("logid");
		 
		 Friend f=new Friend();
		 f.setId(new FriendPK());
		 f.getId().setEmailid(friendId);
		 f.getId().setFriendid(logId);
		 
		 System.out.println("Rejecting...."+f.getId().getEmailid()+"----"+f.getId().getFriendid());
		 
		 
		 EntityManager em=DataProvider.getEntityManager();
		 f=em.find(Friend.class,f.getId());
		 em.getTransaction().begin();
		 em.remove(f);
		 em.getTransaction().commit();
	 
		 return null;
	 }
	 
     public void showRequestedFriends(String friendId)
     {
    	 HttpSession hs=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
 		hs.setAttribute("friendId", friendId);
    	 //String logId=(String)hs.getAttribute("logid");
    	 String sql="Select f from Friend f where f.id.friendid=:logId and f.status=0";
 		EntityManager em=DataProvider.getEntityManager();
 		Query q=em.createQuery(sql);      
 		q.setParameter("logId",friendId);
     }
     
     public String showFriendProfile(String friendId,String fname){
 		//System.out.println("Email id:"+friendId);
 		System.out.println("Got it..");
 		HttpSession hs=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
 		hs.setAttribute("friendId", friendId);
 		hs.setAttribute("fname", fname);
 		hs.removeAttribute("friendsBean");
 		hs.removeAttribute("profileBean");
 		
 		
 		return "friendProfile";
 	}
     
	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
	public List<Registration> getRequestedfriend() {
		HttpSession hs=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		String logid=(String) hs.getAttribute("logid");
		System.out.println("Request sent to"+logid);
		EntityManager em=DataProvider.getEntityManager();
		Query q=em.createNativeQuery("Select * from Friends f, Registration r where f.emailid=r.emailid and f.status=0 and f.emailid='"+logid+"'",Registration.class);

		return q.getResultList();
	}
	
	public List<Registration> getFriendList() {
		HttpSession hs=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		String logId=(String)hs.getAttribute("logid");
		
		String friendId=(String)hs.getAttribute("friendId");
		System.out.println("Friend ID: "+friendId);
		
		if(friendId!=null){
			logId=friendId;
			hs.removeAttribute("profileBean");
		}
		
		
		EntityManager em=DataProvider.getEntityManager();
		Query q1=em.createQuery("Select f from Friend f where f.id.emailid=:emailId and f.status=1");
		q1.setParameter("emailId", logId);
		
		List<Registration> regs=new ArrayList<Registration>();
		for(Friend f:(List<Friend>)q1.getResultList()){
			Query q=em.createQuery("Select r from Registration r where r.emailid=:emailid");
			q.setParameter("emailid", f.getId().getFriendid());
			Registration r=(Registration)q.getSingleResult();
			regs.add(r);
			
		}
		
		return regs;
	}
	
	public List<Registration> getRequestedFriendList() {
		HttpSession hs=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		String logid=(String) hs.getAttribute("logid");
		EntityManager em=DataProvider.getEntityManager();
		Query q1=em.createQuery("Select f from Friend f where f.id.friendid=:emailId and f.status=0");
		q1.setParameter("emailId", logid);
		
		List<Registration> regs=new ArrayList<Registration>();
		for(Friend f:(List<Friend>)q1.getResultList()){
			Query q=em.createQuery("Select r from Registration r where r.emailid=:emailid");
			q.setParameter("emailid", f.getId().getEmailid());
			Registration r=(Registration)q.getSingleResult();
			regs.add(r);
		}
		
		return regs;
	}
	
	public static boolean isAlreadyFriend(String friendId){
		 HttpSession hs=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		 String logId=(String) hs.getAttribute("logid");
		 
		 EntityManager em=DataProvider.getEntityManager();
		 
		 Query q=em.createQuery("Select f from Friend f where f.id.emailid=:emailId and f.id.friendid=:friendId and f.status=1");
		 q.setParameter("emailId", logId);
		 q.setParameter("friendId",friendId);
		 
		 return q.getResultList().size()>0;
	}
	
	public static boolean isFriendRequestSend(String friendId){
		 
		 HttpSession hs=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		 String logId=(String) hs.getAttribute("logid");
		 
		 EntityManager em=DataProvider.getEntityManager();
		 
		 Query q=em.createQuery("Select f from Friend f where ((f.id.emailid=:emailId and f.id.friendid=:friendId and f.status=0) or (f.id.emailid=:friendId and f.id.friendid=:emailId and f.status=0))");
		 q.setParameter("emailId",logId);
		 q.setParameter("friendId",friendId);
		 
		 return q.getResultList().size()>0;
	}
	
	public int getRequestedFriendCount(){
		return getRequestedFriendList().size();
	}
	
}
 
