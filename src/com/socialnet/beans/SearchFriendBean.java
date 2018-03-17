package com.socialnet.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;

import com.socialnet.entity.Registration;
import com.socialnet.util.DataProvider;

public class SearchFriendBean {
	private String searchBy;
	private String keyword;
	private List<Registration> searchResult;
	
	public SearchFriendBean(){
		setSearchBy("name");
		searchResult=new ArrayList<Registration>();
	}

	public void setSearchResult(List<Registration> searchResult) {
		this.searchResult = searchResult;
	}

	public List<Registration> getSearchResult() {
		return searchResult;
	}
	
	public void searchFriendUsingAjax(AjaxBehaviorEvent e){
		searchFriend();
	}
	
	@SuppressWarnings("unchecked")
	public String searchFriend(){
		HttpSession hs=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		String logid=(String)hs.getAttribute("logid");
		
		String sql="";
		if(getSearchBy().equals("name"))
			sql="Select r from Registration r where r.name like :keyword and r.emailid<>:logid";
		else
			sql="Select r from Registration r where r.emailid=:keyword and r.emailid<>:logid";
		
		EntityManager em=DataProvider.getEntityManager();
		Query q=em.createQuery(sql);      
		
		if(getSearchBy().equals("name"))
			q.setParameter("keyword",getKeyword()+"%");
		else
			q.setParameter("keyword",getKeyword());
		
		q.setParameter("logid", logid);
		
		setSearchResult(q.getResultList());
		
		System.out.println("search method called...");
		
		return "searchFriend";
	}
	
	public String showFriendProfile(String friendId,String fname){
		//System.out.println("Email id:"+friendId);
		System.out.println("Got it..");
		HttpSession hs=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		hs.setAttribute("friendId", friendId);
		hs.setAttribute("logname", fname);
		//hs.removeAttribute("searchFriendBean");
		
		
		return "friendProfile";
		
		
		
		
		
	}
	public String goToSearchFriend()
	{
		HttpSession hs=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		hs.removeAttribute("searchFriendBean");
		return "searchFriend1";
	}

	public void setSearchBy(String searchBy) {
		this.searchBy = searchBy;
	}

	public String getSearchBy() {
		return searchBy;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getKeyword() {
		return keyword;
	}
}
