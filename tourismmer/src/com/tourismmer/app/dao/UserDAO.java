package com.tourismmer.app.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import com.tourismmer.app.constants.Messages;
import com.tourismmer.app.model.User;
import com.tourismmer.app.util.EncryptDecryptRSA;
import com.tourismmer.app.util.HibernateUtil;
import com.tourismmer.app.util.Util;

public class UserDAO {
	
	public UserDAO() {
	}
	
	public User create(User userParam) {
		
		try {
			
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			
			session.save(userParam);
			
			userParam.setStatusCode(Messages.SUCCESS.getStatusCode());
			userParam.setStatusText(Messages.SUCCESS.getStatusText());
			
			session.getTransaction().commit();
		
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
			
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			
			User user = (User)session.get(User.class, userParam.getId()); 
			user.setPass(userParam.getPass());
			session.update(user);
			
			userParam.setStatusCode(Messages.SUCCESS.getStatusCode());
			userParam.setStatusText(Messages.SUCCESS.getStatusText());

			session.getTransaction().commit();
			
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
			
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			
			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.eq("email", Util.getString(userParam.getEmail())));
			
			User user = (User)criteria.list().get(0);
			
			if(user != null) {
				
				if(userParam.getPass().equals(EncryptDecryptRSA.decrypt(user.getPass()))) {
					userParam = user;
					userParam.setStatusCode(Messages.SUCCESS.getStatusCode());
					userParam.setStatusText(Messages.SUCCESS.getStatusText());
					
				} else {
					userParam.setStatusCode(Messages.USER_PASS_INVALID.getStatusCode());
					userParam.setStatusText(Messages.USER_PASS_INVALID.getStatusText());
				}
				
			} else {
				userParam = new User();
				userParam.setStatusCode(Messages.USER_NOT_REGISTERED.getStatusCode());
				userParam.setStatusText(Messages.USER_NOT_REGISTERED.getStatusText());
			}
			
			session.getTransaction().commit();
		
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
			
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			
			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.eq("facebookId", Util.getString(userParam.getEmail())));
			
			User user = (User)criteria.list().get(0);
			
			if(user != null) {
				
				userParam = user;
				userParam.setStatusCode(Messages.SUCCESS.getStatusCode());
				userParam.setStatusText(Messages.SUCCESS.getStatusText());
					
			} else {
				userParam.setStatusCode(Messages.QUERY_NOT_FOUND.getStatusCode());
				userParam.setStatusText(Messages.QUERY_NOT_FOUND.getStatusText());
			}
		
			session.getTransaction().commit();
			
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
			
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			
			User user = (User) session.get(User.class, userParam.getId());
			
			if(user != null) {
				userParam = user;
				userParam.setStatusCode(Messages.SUCCESS.getStatusCode());
				userParam.setStatusText(Messages.SUCCESS.getStatusText());
					
			} else {
				userParam.setStatusCode(Messages.QUERY_NOT_FOUND.getStatusCode());
				userParam.setStatusText(Messages.QUERY_NOT_FOUND.getStatusText());
			}
			
			session.getTransaction().commit();
		
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
			
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			
			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.eq("email", Util.getString(userParam.getEmail())));
			
			User user = (User)criteria.list().get(0);
			
			if(user != null) {
				userParam = user;
				userParam.setStatusCode(Messages.SUCCESS.getStatusCode());
				userParam.setStatusText(Messages.SUCCESS.getStatusText());
					
			} else {
				userParam.setStatusCode(Messages.QUERY_NOT_FOUND.getStatusCode());
				userParam.setStatusText(Messages.QUERY_NOT_FOUND.getStatusText());
			}
			
			session.getTransaction().commit();
			
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
