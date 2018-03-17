package com.socialnet.util;

import javax.persistence.*;
public class DataProvider {
	public static EntityManager getEntityManager(){
		return Persistence.createEntityManagerFactory("socialnetworking").createEntityManager();		
	}
}
