package com.tourismmer.app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.exception.ConstraintViolationException;

import com.tourismmer.app.constants.Messages;
import com.tourismmer.app.model.Image;

public class ImageDAO {
	
	public Image getImageRandom() {
		
		Image imageReturn = new Image();
		
		try {
			
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("Image");
			EntityManager manager = factory.createEntityManager();
			
			Query query = manager.createQuery("select i from Image as i order by rand()");
			
			
			@SuppressWarnings("unchecked")
			List<Image> list = query.getResultList();
			
			if(list.size()>0) {
				
				imageReturn = list.get(0);
				imageReturn.setStatusCode(Messages.SUCCESS.getStatusCode());
				imageReturn.setStatusText(Messages.SUCCESS.getStatusText());
					
			} else {
				imageReturn.setStatusCode(Messages.QUERY_NOT_FOUND.getStatusCode());
				imageReturn.setStatusText(Messages.QUERY_NOT_FOUND.getStatusText());
			}
			
			manager.close();
		
		} catch (Exception e) {
			
			if(e.getCause() instanceof ConstraintViolationException) {
				String msg = ((ConstraintViolationException) e.getCause()).getSQLException().getMessage();
				imageReturn.setStatusCode(Messages.CONSTRAINT_VIOLATION_EXCEPTION.getStatusCode());
				imageReturn.setStatusText(msg);
			
			} else {
				imageReturn.setStatusCode(Messages.ERROR_QUERYING_DATABASE.getStatusCode());
				imageReturn.setStatusText(e.getMessage());
			}
			
			Log log = LogFactory.getLog(GroupDAO.class);
			log.error(e);
		}
		
		return imageReturn;
		
	}

}
