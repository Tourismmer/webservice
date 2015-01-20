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
import com.tourismmer.app.model.User;
import com.tourismmer.app.util.EncryptDecryptRSA;
import com.tourismmer.app.util.Util;

public class UserDAO {
	
	public UserDAO() {
	}
	
	public User create(User userParam) {
		
		try {
		
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("User");
			EntityManager manager = factory.createEntityManager();
			
			manager.getTransaction().begin();    
			manager.persist(userParam);
			manager.getTransaction().commit();
			
			userParam.setStatusCode(Messages.SUCCESS.getStatusCode());
			userParam.setStatusText(Messages.SUCCESS.getStatusText());
			
			manager.close();
		
		} catch (Exception e) {
			
			if(e.getCause() instanceof ConstraintViolationException) {
				String msg = ((ConstraintViolationException) e.getCause()).getSQLException().getMessage();
				userParam.setStatusCode(Messages.CONSTRAINT_VIOLATION_EXCEPTION.getStatusCode());
				userParam.setStatusText(msg);
			
			} else {
				userParam.setStatusCode(Messages.ERROR_QUERYING_DATABASE.getStatusCode());
				userParam.setStatusText(e.getMessage());
			}
			
			Log log = LogFactory.getLog(UserDAO.class);
			log.error(e);
		}
		
		return userParam;
		
	}
	
//	public User update(User userParam) {
//		
//		try {
//		
//			EntityManagerFactory factory = Persistence.createEntityManagerFactory("User");
//			EntityManager manager = factory.createEntityManager();
//			
//			manager.getTransaction().begin();    
//			manager.merge(userParam);
//			manager.getTransaction().commit(); 
//			
//			userParam.setStatusCode(Messages.SUCCESS.getStatusCode());
//			userParam.setStatusText(Messages.SUCCESS.getStatusText());
//			
//			manager.close();
//		
//		} catch (Exception e) {
//			
//			if(e.getCause() instanceof ConstraintViolationException) {
//				String msg = ((ConstraintViolationException) e.getCause()).getSQLException().getMessage();
//				userParam.setStatusCode(Messages.CONSTRAINT_VIOLATION_EXCEPTION.getStatusCode());
//				userParam.setStatusText(msg);
//			
//			} else {
//				userParam.setStatusCode(Messages.ERROR_QUERYING_DATABASE.getStatusCode());
//				userParam.setStatusText(e.getMessage());
//			}
//			
//			Log log = LogFactory.getLog(UserDAO.class);
//			log.error(e);
//		}
//		
//		return userParam;
//		
//	}
	
	public User changePass(User userParam) {
		
		try {
		
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("User");
			EntityManager manager = factory.createEntityManager();
			
			manager.getTransaction().begin();
			
			Query query = manager.createQuery("UPDATE User u SET u.pass = :pass WHERE u.id = :id");
			
			query.setParameter("id", userParam.getId());
			query.setParameter("pass", userParam.getPass());
			
			query.executeUpdate();
			manager.getTransaction().commit(); 
			
			userParam.setStatusCode(Messages.SUCCESS.getStatusCode());
			userParam.setStatusText(Messages.SUCCESS.getStatusText());
			
			manager.close();
		
		} catch (Exception e) {
			
			if(e.getCause() instanceof ConstraintViolationException) {
				String msg = ((ConstraintViolationException) e.getCause()).getSQLException().getMessage();
				userParam.setStatusCode(Messages.CONSTRAINT_VIOLATION_EXCEPTION.getStatusCode());
				userParam.setStatusText(msg);
			
			} else {
				userParam.setStatusCode(Messages.ERROR_QUERYING_DATABASE.getStatusCode());
				userParam.setStatusText(Messages.ERROR_QUERYING_DATABASE.getStatusText());
			}
			
			Log log = LogFactory.getLog(UserDAO.class);
			log.error(e);
		}
		
		return userParam;
		
	}
	
	public User login(User userParam) {
		
		try {
		
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("User");
			EntityManager manager = factory.createEntityManager();
			
			Query query = manager.createQuery("select u from User as u where u.email = ?");
			
			query.setParameter(1, Util.getString(userParam.getEmail()));
			
			@SuppressWarnings("unchecked")
			List<User> list = query.getResultList();
			
			if(list.size()>0) {
				
				if(userParam.getPass().equals(EncryptDecryptRSA.decrypt(list.get(0).getPass()))) {
					userParam = list.get(0);
					userParam.setStatusCode(Messages.SUCCESS.getStatusCode());
					userParam.setStatusText(Messages.SUCCESS.getStatusText());
					
				} else {
					userParam.setStatusCode(Messages.USER_PASS_INVALID.getStatusCode());
					userParam.setStatusText(Messages.USER_PASS_INVALID.getStatusText());
				}
				
			} else {
				userParam.setStatusCode(Messages.USER_NOT_REGISTERED.getStatusCode());
				userParam.setStatusText(Messages.USER_NOT_REGISTERED.getStatusText());
			}
			
			manager.close();
		
		} catch (Exception e) {
			
			if(e.getCause() instanceof ConstraintViolationException) {
				String msg = ((ConstraintViolationException) e.getCause()).getSQLException().getMessage();
				userParam.setStatusCode(Messages.CONSTRAINT_VIOLATION_EXCEPTION.getStatusCode());
				userParam.setStatusText(msg);
			
			} else {
				userParam.setStatusCode(Messages.ERROR_QUERYING_DATABASE.getStatusCode());
				userParam.setStatusText(Messages.ERROR_QUERYING_DATABASE.getStatusText());
			}
			
			Log log = LogFactory.getLog(UserDAO.class);
			log.error(e);
		}
		
		return userParam;
		
	}
	
	public User loginFacebook(User userParam) {
		
		try {
		
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("User");
			EntityManager manager = factory.createEntityManager();
			
			Query query = manager.createQuery("select u from User as u where u.facebookId = ?");
			query.setParameter(1, Util.getString(userParam.getFacebookId()));
			
			@SuppressWarnings("unchecked")
			List<User> list = query.getResultList();
			
			if(list.size()>0) {
				
				userParam = list.get(0);
				userParam.setStatusCode(Messages.SUCCESS.getStatusCode());
				userParam.setStatusText(Messages.SUCCESS.getStatusText());
					
			} else {
				userParam.setStatusCode(Messages.QUERY_NOT_FOUND.getStatusCode());
				userParam.setStatusText(Messages.QUERY_NOT_FOUND.getStatusText());
			}
			
			manager.close();
		
		} catch (Exception e) {
			
			if(e.getCause() instanceof ConstraintViolationException) {
				String msg = ((ConstraintViolationException) e.getCause()).getSQLException().getMessage();
				userParam.setStatusCode(Messages.CONSTRAINT_VIOLATION_EXCEPTION.getStatusCode());
				userParam.setStatusText(msg);
			
			} else {
				userParam.setStatusCode(Messages.ERROR_QUERYING_DATABASE.getStatusCode());
				userParam.setStatusText(Messages.ERROR_QUERYING_DATABASE.getStatusText());
			}
			
			Log log = LogFactory.getLog(UserDAO.class);
			log.error(e);
		}
		
		return userParam;
		
	}
	
	public User getUser(User userParam) {
		
		try {
			
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("User");
			EntityManager manager = factory.createEntityManager();
			
			Query query = manager.createQuery("select u from User as u where u.id = ?");
			query.setParameter(1, Util.getLong(userParam.getId()));
			
			@SuppressWarnings("unchecked")
			List<User> list = query.getResultList();
			
			if(list.size()>0) {
				
				userParam = list.get(0);
				userParam.setStatusCode(Messages.SUCCESS.getStatusCode());
				userParam.setStatusText(Messages.SUCCESS.getStatusText());
					
			} else {
				userParam.setStatusCode(Messages.QUERY_NOT_FOUND.getStatusCode());
				userParam.setStatusText(Messages.QUERY_NOT_FOUND.getStatusText());
			}
			
			manager.close();
		
		} catch (Exception e) {
			
			if(e.getCause() instanceof ConstraintViolationException) {
				String msg = ((ConstraintViolationException) e.getCause()).getSQLException().getMessage();
				userParam.setStatusCode(Messages.CONSTRAINT_VIOLATION_EXCEPTION.getStatusCode());
				userParam.setStatusText(msg);
			
			} else {
				userParam.setStatusCode(Messages.ERROR_QUERYING_DATABASE.getStatusCode());
				userParam.setStatusText(Messages.ERROR_QUERYING_DATABASE.getStatusText());
			}
			
			Log log = LogFactory.getLog(UserDAO.class);
			log.error(e);
		}
		
		return userParam;
		
	}
	
	public User getUserByEmail(String email) {
		User userParam = new User();
		
		try {
			
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("User");
			EntityManager manager = factory.createEntityManager();
			
			Query query = manager.createQuery("select u from User as u where u.email = ?");
			query.setParameter(1, Util.getString(email));
			
			@SuppressWarnings("unchecked")
			List<User> list = query.getResultList();
			
			if(list.size()>0) {
				
				userParam = list.get(0);
				userParam.setStatusCode(Messages.SUCCESS.getStatusCode());
				userParam.setStatusText(Messages.SUCCESS.getStatusText());
					
			} else {
				userParam.setStatusCode(Messages.QUERY_NOT_FOUND.getStatusCode());
				userParam.setStatusText(Messages.QUERY_NOT_FOUND.getStatusText());
			}
			
			manager.close();
		
		} catch (Exception e) {
			
			if(e.getCause() instanceof ConstraintViolationException) {
				String msg = ((ConstraintViolationException) e.getCause()).getSQLException().getMessage();
				userParam.setStatusCode(Messages.CONSTRAINT_VIOLATION_EXCEPTION.getStatusCode());
				userParam.setStatusText(msg);
			
			} else {
				userParam.setStatusCode(Messages.ERROR_QUERYING_DATABASE.getStatusCode());
				userParam.setStatusText(Messages.ERROR_QUERYING_DATABASE.getStatusText());
			}
			
			Log log = LogFactory.getLog(UserDAO.class);
			log.error(e);
		}
		
		return userParam;
		
	}

}
