package com.socialnet.beans;

import com.socialnet.entity.Album;
import com.socialnet.util.DataProvider;

import java.util.*;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
public class AlbumBean {
	private Album album;
	private EntityManager em;
	
	public AlbumBean(){
		setAlbum(new Album());
		em=DataProvider.getEntityManager();
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public Album getAlbum() {
		return album;
	}
	
	@SuppressWarnings("unchecked")
	public List<Album> getAlbumList(){
		return em.createQuery("Select alb from Album alb").getResultList();
	}
	public void addAlbum(ActionEvent e)
	{
		HttpSession hs=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		 String logId=(String) hs.getAttribute("logid");
		 EntityManager em=DataProvider.getEntityManager();
	
		album.setEmailid(logId);
		album.setDateOfCreation(new Date());
		//album.setShow_To_Groups("aa");
		em.getTransaction().begin();
		em.persist(album);
		em.getTransaction().commit();
		
	}
}
