package com.tourismmer.app.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tourismmer.app.constants.Messages;
import com.tourismmer.app.model.Comment;

public class CommentDAO {
	
	public Comment create(Comment commentParam) {
		
		try {
		
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("Comment");
			EntityManager manager = factory.createEntityManager();
			
			manager.getTransaction().begin();
			manager.persist(commentParam);
			manager.getTransaction().commit(); 
			
			commentParam.setStatusCode(Messages.SUCCESS.getStatusCode());
			commentParam.setStatusText(Messages.SUCCESS.getStatusText());
			
			manager.close();
		
		} catch (Exception e) {
			commentParam.setStatusCode(Messages.ERROR_QUERYING_DATABASE.getStatusCode());
			commentParam.setStatusText(Messages.ERROR_QUERYING_DATABASE.getStatusText());
			Log log = LogFactory.getLog(UserDAO.class);
			log.error(e);
		}
		
		return commentParam;
		
	}

}
