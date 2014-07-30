package com.tourismmer.app.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.tourismmer.app.model.User;

public class UserDAO {
	
	public UserDAO() {
	}
	
	public User register(User userParam) {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("User");
		EntityManager manager = factory.createEntityManager();
		
		manager.getTransaction().begin();    
		manager.persist(userParam);
		userParam.getId();
		
		manager.getTransaction().commit(); 
		
		manager.close();
		
		return userParam;
		
	}

}
