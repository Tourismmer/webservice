package com.tourismmer.app.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tourismmer.app.constants.Messages;
import com.tourismmer.app.model.Post;

public class PostDAO {

	public Post create(Post postParam) {
		
		try {
		
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("Post");
			EntityManager manager = factory.createEntityManager();
			
			manager.getTransaction().begin();
			manager.persist(postParam);
			manager.getTransaction().commit(); 
			
			postParam.setStatusCode(Messages.SUCCESS.getStatusCode());
			postParam.setStatusText(Messages.SUCCESS.getStatusText());
			
			manager.close();
		
		} catch (Exception e) {
			postParam.setStatusCode(Messages.ERROR_QUERYING_DATABASE.getStatusCode());
			postParam.setStatusText(Messages.ERROR_QUERYING_DATABASE.getStatusText());
			Log log = LogFactory.getLog(UserDAO.class);
			log.error(e);
		}
		
		return postParam;
		
	}

}
