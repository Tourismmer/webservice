package com.tourismmer.app.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tourismmer.app.constants.Messages;
import com.tourismmer.app.model.Group;

public class GroupDAO {
	
	public GroupDAO() {
	}
	
	public Group create(Group tripParam) {
		
		try {
		
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("Trip");
			EntityManager manager = factory.createEntityManager();
			
			manager.getTransaction().begin();
			manager.persist(tripParam);
			manager.getTransaction().commit(); 
			
			tripParam.setStatusCode(Messages.SUCCESS.getStatusCode());
			tripParam.setStatusText(Messages.SUCCESS.getStatusText());
			
			manager.close();
		
		} catch (Exception e) {
			tripParam.setStatusCode(Messages.ERROR_QUERYING_DATABASE.getStatusCode());
			tripParam.setStatusText(Messages.ERROR_QUERYING_DATABASE.getStatusText());
			Log log = LogFactory.getLog(UserDAO.class);
			log.error(e);
		}
		
		return tripParam;
		
	}

}