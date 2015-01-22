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
import com.tourismmer.app.model.ListGroup;
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
			Log log = LogFactory.getLog(GroupDAO.class);
			log.error(e);
		}
		
		return groupParam;
		
	}
	
	public Group getGroup(Group groupParam) {
		
		try {
			
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("Group");
			EntityManager manager = factory.createEntityManager();
			
			Query query = manager.createQuery("select g from Group as g where g.id = ?");
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
	
	
	
	public Group update(Group groupParam) {
		
		try {
		
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("Group");
			EntityManager manager = factory.createEntityManager();
			
			manager.getTransaction().begin();
			
			Query query = manager.createQuery("UPDATE Group g SET g.destination = :destination, g.purpose.id = :purpose WHERE g.id = :id");
			
			query.setParameter("id", groupParam.getId());
			query.setParameter("destination", groupParam.getDestination());
			query.setParameter("purpose", groupParam.getPurpose().getId());
			
			query.executeUpdate();
			
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
			
			Log log = LogFactory.getLog(GroupDAO.class);
			log.error(e);
		}
		
		return groupParam;
		
	}
	
	public ListGroup getTopTrips(Integer amount) {
		
		ListGroup listGroup = new ListGroup();
		
		try {
			
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("Group");
			EntityManager manager = factory.createEntityManager();
			
			Query query = manager.createQuery("Select g from Group g");
			query.setMaxResults(amount);
			
			@SuppressWarnings("unchecked")
			List<Group> list = query.getResultList();
			
			if(Util.isEmptyOrNull(list)) {
				listGroup.setStatusCode(Messages.QUERY_NOT_FOUND.getStatusCode());
				listGroup.setStatusText(Messages.QUERY_NOT_FOUND.getStatusText());
				return listGroup;
			}
				
			for (Group group : list) {
				listGroup.getListGroup().add(group);
			}
			
			listGroup.setStatusCode(Messages.SUCCESS.getStatusCode());
			listGroup.setStatusText(Messages.SUCCESS.getStatusText());
				
			
			manager.close();
		
		} catch (Exception e) {
			
			if(e.getCause() instanceof ConstraintViolationException) {
				String msg = ((ConstraintViolationException) e.getCause()).getSQLException().getMessage();
				listGroup.setStatusCode(Messages.CONSTRAINT_VIOLATION_EXCEPTION.getStatusCode());
				listGroup.setStatusText(msg);
			
			} else {
				listGroup.setStatusCode(Messages.ERROR_QUERYING_DATABASE.getStatusCode());
				listGroup.setStatusText(e.getMessage());
			}
			
			Log log = LogFactory.getLog(GroupDAO.class);
			log.error(e);
		}
		
		return listGroup;
	}
	
	public ListGroup getUserGroups(Long idUser, Integer amount, Integer firstResult) {
		
		ListGroup listGroup = new ListGroup();
		
		try {
			
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("Group");
			EntityManager manager = factory.createEntityManager();
			
			
			Query query = manager.createQuery("Select g from Group g where g.owner.id = ?");
			query.setParameter(1, Util.getLong(idUser));
			query.setFirstResult(firstResult);
			query.setMaxResults(amount);
			
			@SuppressWarnings("unchecked")
			List<Group> list = query.getResultList();
			
			if(Util.isEmptyOrNull(list)) {
				listGroup.setStatusCode(Messages.QUERY_NOT_FOUND.getStatusCode());
				listGroup.setStatusText(Messages.QUERY_NOT_FOUND.getStatusText());
				return listGroup;
			}
				
			for (Group group : list) {
				listGroup.getListGroup().add(group);
			}
			
			listGroup.setStatusCode(Messages.SUCCESS.getStatusCode());
			listGroup.setStatusText(Messages.SUCCESS.getStatusText());
				
			
			manager.close();
		
		} catch (Exception e) {
			
			if(e.getCause() instanceof ConstraintViolationException) {
				String msg = ((ConstraintViolationException) e.getCause()).getSQLException().getMessage();
				listGroup.setStatusCode(Messages.CONSTRAINT_VIOLATION_EXCEPTION.getStatusCode());
				listGroup.setStatusText(msg);
			
			} else {
				listGroup.setStatusCode(Messages.ERROR_QUERYING_DATABASE.getStatusCode());
				listGroup.setStatusText(e.getMessage());
			}
			
			Log log = LogFactory.getLog(GroupDAO.class);
			log.error(e);
		}
		
		return listGroup;
	}
	
}
