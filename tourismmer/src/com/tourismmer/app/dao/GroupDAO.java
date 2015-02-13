package com.tourismmer.app.dao;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import com.tourismmer.app.constants.Messages;
import com.tourismmer.app.model.Group;
import com.tourismmer.app.model.GroupUser;
import com.tourismmer.app.model.ListGroup;
import com.tourismmer.app.util.HibernateUtil;
import com.tourismmer.app.util.Util;

public class GroupDAO {
	
	public GroupDAO() {
	}
	
	public Group create(Group groupParam) {
		
		try {
			
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			
			Query query = session.createQuery("from Group g where month(g.date) = :month and year(g.date) = :year and g.purpose.id = :idPurpose and g.destination = :destination");
			
			query.setParameter("month", groupParam.getDate().get(Calendar.MONTH)+1);
			query.setParameter("year", groupParam.getDate().get(Calendar.YEAR));
			query.setParameter("idPurpose", groupParam.getPurpose().getId());
			query.setParameter("destination", groupParam.getDestination());
			
			@SuppressWarnings("rawtypes")
			List listGroup = query.list();
			
			Group group = null;
			if(Util.isNotEmptyOrNull(listGroup)) {
				
				group = (Group) listGroup.get(0);
				
				GroupUser groupUser = new GroupUser();
				groupUser.setIdGroup(group.getId());
				groupUser.setIdUser(groupParam.getOwner().getId());
				
				session.save(groupUser);
				
				groupParam = (Group) session.get(Group.class, groupParam.getId());
				
			} else {
				session.save(groupParam);
			}
			
			groupParam.setStatusCode(Messages.SUCCESS.getStatusCode());
			groupParam.setStatusText(Messages.SUCCESS.getStatusText());
			
			session.getTransaction().commit();
			session.close();
		
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
			
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			
			Group group = (Group) session.get(Group.class, groupParam.getId());
			
			if(group != null) {
				
				groupParam.setId(group.getId());
				groupParam.setDestination(group.getDestination());
				groupParam.setPurpose(group.getPurpose());
				groupParam.setOwner(group.getOwner());
				groupParam.setImage(group.getImage());
				groupParam.setDate(group.getDate());
				
				int countUser = session.createQuery("from GroupUser g where g.idGroup = :idGroup")
						.setParameter("idGroup", groupParam.getId()).list().size();
				groupParam.setCountUser(countUser);
				
				groupParam.setStatusCode(Messages.SUCCESS.getStatusCode());
				groupParam.setStatusText(Messages.SUCCESS.getStatusText());
					
			} else {
				groupParam.setStatusCode(Messages.QUERY_NOT_FOUND.getStatusCode());
				groupParam.setStatusText(Messages.QUERY_NOT_FOUND.getStatusText());
			}
			
			session.getTransaction().commit();
			session.close();
			
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
	
	
	
//	public Group update(Group groupParam) {
//		
//		try {
//		
//			EntityManagerFactory factory = Persistence.createEntityManagerFactory("Group");
//			EntityManager manager = factory.createEntityManager();
//			
//			manager.getTransaction().begin();
//			
//			Query query = manager.createQuery("UPDATE Group g SET g.destination = :destination, g.purpose.id = :purpose WHERE g.id = :id");
//			
//			query.setParameter("id", groupParam.getId());
//			query.setParameter("destination", groupParam.getDestination());
//			query.setParameter("purpose", groupParam.getPurpose().getId());
//			
//			query.executeUpdate();
//			
//			manager.getTransaction().commit(); 
//			
//			groupParam.setStatusCode(Messages.SUCCESS.getStatusCode());
//			groupParam.setStatusText(Messages.SUCCESS.getStatusText());
//			
//			manager.close();
//		
//		} catch (Exception e) {
//			
//			if(e.getCause() instanceof ConstraintViolationException) {
//				String msg = ((ConstraintViolationException) e.getCause()).getSQLException().getMessage();
//				groupParam.setStatusCode(Messages.CONSTRAINT_VIOLATION_EXCEPTION.getStatusCode());
//				groupParam.setStatusText(msg);
//			
//			} else {
//				groupParam.setStatusCode(Messages.ERROR_QUERYING_DATABASE.getStatusCode());
//				groupParam.setStatusText(e.getMessage());
//			}
//			
//			Log log = LogFactory.getLog(GroupDAO.class);
//			log.error(e);
//		}
//		
//		return groupParam;
//		
//	}
	
	public ListGroup getTopTrips(Integer amount, Integer fistResult) {
		
		ListGroup listGroup = new ListGroup();
		
		try {
			
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			
			Query query = session.createQuery("from Group");
			query.setMaxResults(amount);
			query.setFirstResult(fistResult);
			
			@SuppressWarnings("unchecked")
			List<Group> list = query.list();
			
			if(Util.isEmptyOrNull(list)) {
				listGroup.setStatusCode(Messages.QUERY_NOT_FOUND.getStatusCode());
				listGroup.setStatusText(Messages.QUERY_NOT_FOUND.getStatusText());
				return listGroup;
			}
			
			Group group = null;
			
			for (Group g : list) {
				group = new Group();
				group.setId(g.getId());
				group.setDestination(g.getDestination());
				group.setPurpose(g.getPurpose());
				group.setOwner(g.getOwner());
				group.setImage(g.getImage());
				group.setDate(g.getDate());
				
				int countUser = session.createQuery("from GroupUser g where g.idGroup = :idGroup")
						.setParameter("idGroup", group.getId()).list().size();
				group.setCountUser(countUser);
				
				listGroup.getListGroup().add(group);
			}
			
			listGroup.setStatusCode(Messages.SUCCESS.getStatusCode());
			listGroup.setStatusText(Messages.SUCCESS.getStatusText());
			
			session.getTransaction().commit();
			session.close();
		
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
			
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			
			Query query = session.createQuery("from Group g where g.owner.id = :idOwner");
			query.setParameter("idOwner", Util.getLong(idUser));
			query.setMaxResults(amount);
			query.setFirstResult(firstResult);
			
			@SuppressWarnings("unchecked")
			List<Group> list = query.list();
			
			if(Util.isEmptyOrNull(list)) {
				listGroup.setStatusCode(Messages.QUERY_NOT_FOUND.getStatusCode());
				listGroup.setStatusText(Messages.QUERY_NOT_FOUND.getStatusText());
				return listGroup;
			}
				
			Group group = null;
			
			for (Group g : list) {
				group = new Group();
				group.setId(g.getId());
				group.setDestination(g.getDestination());
				group.setPurpose(g.getPurpose());
				group.setOwner(g.getOwner());
				group.setImage(g.getImage());
				group.setDate(g.getDate());
				
				int countUser = session.createQuery("from GroupUser g where g.idGroup = :idGroup")
						.setParameter("idGroup", group.getId()).list().size();
				group.setCountUser(countUser);
				
				listGroup.getListGroup().add(group);
			}
			
			listGroup.setStatusCode(Messages.SUCCESS.getStatusCode());
			listGroup.setStatusText(Messages.SUCCESS.getStatusText());
				
			session.getTransaction().commit();
			session.close();
			
			list.size();
		
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
	
	public GroupUser join(GroupUser groupUserParam) {
		
		try {
			
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			
			session.save(groupUserParam);
		
			groupUserParam.setStatusCode(Messages.SUCCESS.getStatusCode());
			groupUserParam.setStatusText(Messages.SUCCESS.getStatusText());
			
			session.getTransaction().commit();
			session.close();
		
		} catch (Exception e) {
			groupUserParam.setStatusCode(Messages.ERROR_QUERYING_DATABASE.getStatusCode());
			groupUserParam.setStatusText(Messages.ERROR_QUERYING_DATABASE.getStatusText());
			Log log = LogFactory.getLog(PostDAO.class);
			log.error(e);
		}
		
		return groupUserParam;
	}
	
}
