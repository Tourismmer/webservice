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
import com.tourismmer.app.model.Group;
import com.tourismmer.app.model.Image;
import com.tourismmer.app.util.Util;

public class GroupDAO {
	
	public GroupDAO() {
	}
	
	public Group create(Group groupParam) {
		
		try {
		
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("Group");
			EntityManager manager = factory.createEntityManager();
			
			manager.getTransaction().begin();
			manager.persist(groupParam);
			manager.getTransaction().commit(); 
			
			groupParam.setStatusCode(Messages.SUCCESS.getStatusCode());
			groupParam.setStatusText(Messages.SUCCESS.getStatusText());
			
			manager.close();
		
		} catch (Exception e) {
			groupParam.setStatusCode(Messages.ERROR_QUERYING_DATABASE.getStatusCode());
			groupParam.setStatusText(Messages.ERROR_QUERYING_DATABASE.getStatusText());
			Log log = LogFactory.getLog(UserDAO.class);
			log.error(e);
		}
		
		return groupParam;
		
	}
	
	public Group getGroup(Group groupParam) {
		
		try {
			
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("Group");
			EntityManager manager = factory.createEntityManager();
			
			Query query = manager.createQuery("select u from Group as u where u.id = ?");
			query.setParameter(1, Util.getLong(groupParam.getId()));
			
			@SuppressWarnings("unchecked")
			List<Group> list = query.getResultList();
			
			if(list.size()>0) {
				
				groupParam = list.get(0);
				groupParam.setStatusCode(Messages.SUCCESS.getStatusCode());
				groupParam.setStatusText(Messages.SUCCESS.getStatusText());
					
			} else {
				groupParam.setStatusCode(Messages.QUERY_NOT_FOUND.getStatusCode());
				groupParam.setStatusText(Messages.QUERY_NOT_FOUND.getStatusText());
			}
			
			manager.close();
		
		} catch (Exception e) {
			
			if(e.getCause() instanceof ConstraintViolationException) {
				String msg = ((ConstraintViolationException) e.getCause()).getSQLException().getMessage();
				groupParam.setStatusCode(Messages.CONSTRAINT_VIOLATION_EXCEPTION.getStatusCode());
				groupParam.setStatusText(msg);
			
			} else {
				groupParam.setStatusCode(Messages.ERROR_QUERYING_DATABASE.getStatusCode());
				groupParam.setStatusText(e.getMessage());
			}
			
			Log log = LogFactory.getLog(GroupDAO.class);
			log.error(e);
		}
		
		return groupParam;
		
	}
	
	public Image getImageGroup() {
		
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
	
	public Group update(Group groupParam) {
		
		try {
		
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("User");
			EntityManager manager = factory.createEntityManager();
			
			manager.getTransaction().begin();    
			manager.merge(groupParam);
			manager.getTransaction().commit(); 
			
			groupParam.setStatusCode(Messages.SUCCESS.getStatusCode());
			groupParam.setStatusText(Messages.SUCCESS.getStatusText());
			
			manager.close();
		
		} catch (Exception e) {
			
			if(e.getCause() instanceof ConstraintViolationException) {
				String msg = ((ConstraintViolationException) e.getCause()).getSQLException().getMessage();
				groupParam.setStatusCode(Messages.CONSTRAINT_VIOLATION_EXCEPTION.getStatusCode());
				groupParam.setStatusText(msg);
			
			} else {
				groupParam.setStatusCode(Messages.ERROR_QUERYING_DATABASE.getStatusCode());
				groupParam.setStatusText(e.getMessage());
			}
			
			Log log = LogFactory.getLog(UserDAO.class);
			log.error(e);
		}
		
		return groupParam;
		
	}

}
