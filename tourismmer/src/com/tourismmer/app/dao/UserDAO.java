package com.tourismmer.app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.tourismmer.app.constants.Messages;
import com.tourismmer.app.model.User;
import com.tourismmer.app.util.Util;

public class UserDAO {
	
	public UserDAO() {
	}
	
	public User register(User userParam) {
		
		try {
		
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("User");
			EntityManager manager = factory.createEntityManager();
			
			manager.getTransaction().begin();    
			manager.persist(userParam);
			userParam.getId();
			
			manager.getTransaction().commit(); 
			
			userParam.setStatusCode(Messages.REGISTER_SUCCESS.getStatusCode());
			userParam.setStatusText(Messages.REGISTER_SUCCESS.getStatusText());
			
			manager.close();
		
		} catch (Exception e) {
			userParam.setStatusCode(Messages.ERROR_QUERYING_DATABASE.getStatusCode());
			userParam.setStatusText(Messages.ERROR_QUERYING_DATABASE.getStatusText());
		}
		
		return userParam;
		
	}
	
	public User login(User userParam) {
		
		try {
		
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("User");
			EntityManager manager = factory.createEntityManager();
			
			Query query = manager.createQuery("select u from User as u where u.name = ?");
			
			query.setParameter(1, Util.getString(userParam.getName()));
			
			List<User> list = query.getResultList();
			
			if(list.size()>0) {
				userParam.setStatusCode(Messages.USER_LOGGED.getStatusCode());
				userParam.setStatusText(Messages.USER_LOGGED.getStatusText());
				userParam = list.get(0);
			} else {
				userParam.setStatusCode(Messages.USER_PASS_INVALID.getStatusCode());
				userParam.setStatusText(Messages.USER_PASS_INVALID.getStatusText());
			}
			
			manager.close();
		
		} catch (Exception e) {
			userParam.setStatusCode(Messages.ERROR_QUERYING_DATABASE.getStatusCode());
			userParam.setStatusText(Messages.ERROR_QUERYING_DATABASE.getStatusText());
		}
		
		return userParam;
		
	}

}
