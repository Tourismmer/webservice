package com.tourismmer.app.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import com.tourismmer.app.constants.Messages;
import com.tourismmer.app.model.ListPost;
import com.tourismmer.app.model.Post;
import com.tourismmer.app.util.HibernateUtil;
import com.tourismmer.app.util.Util;

public class PostDAO {

	public Post create(Post postParam) {
		
		try {
			
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			
			session.save(postParam);
		
			postParam.setStatusCode(Messages.SUCCESS.getStatusCode());
			postParam.setStatusText(Messages.SUCCESS.getStatusText());
			
			session.getTransaction().commit();
		
		} catch (Exception e) {
			postParam.setStatusCode(Messages.ERROR_QUERYING_DATABASE.getStatusCode());
			postParam.setStatusText(Messages.ERROR_QUERYING_DATABASE.getStatusText());
			Log log = LogFactory.getLog(PostDAO.class);
			log.error(e);
		}
		
		return postParam;
		
	}
	
	public Post getPost(Post postParam) {
		
		try {
			
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			
			Post post = (Post) session.get(Post.class, postParam.getId());
			
			if(post != null) {
				
				postParam = post;
				postParam.setStatusCode(Messages.SUCCESS.getStatusCode());
				postParam.setStatusText(Messages.SUCCESS.getStatusText());
					
			} else {
				postParam.setStatusCode(Messages.QUERY_NOT_FOUND.getStatusCode());
				postParam.setStatusText(Messages.QUERY_NOT_FOUND.getStatusText());
			}
			
			session.getTransaction().commit();
		
		} catch (Exception e) {
			
			if(e.getCause() instanceof ConstraintViolationException) {
				String msg = ((ConstraintViolationException) e.getCause()).getSQLException().getMessage();
				postParam.setStatusCode(Messages.CONSTRAINT_VIOLATION_EXCEPTION.getStatusCode());
				postParam.setStatusText(msg);
			
			} else {
				postParam.setStatusCode(Messages.ERROR_QUERYING_DATABASE.getStatusCode());
				postParam.setStatusText(e.getMessage());
			}
			
			Log log = LogFactory.getLog(PostDAO.class);
			log.error(e);
		}
		
		return postParam;
		
	}
	
	public ListPost getListPost(Long idUser, Long idGroup, Integer amount, Integer firstResult) {
		
		ListPost listPost = new ListPost();
		
		try {
			
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			
			Query query = session.createQuery("from Post p where p.author.id = :idUser and p.group.id = :idGroup");
			query.setParameter("idUser", Util.getLong(idUser));
			query.setParameter("idGroup", Util.getLong(idGroup));
			query.setMaxResults(amount);
			query.setFirstResult(firstResult);
			
			@SuppressWarnings("unchecked")
			List<Post> list = query.list();
			
			if(Util.isEmptyOrNull(list)) {
				listPost.setStatusCode(Messages.QUERY_NOT_FOUND.getStatusCode());
				listPost.setStatusText(Messages.QUERY_NOT_FOUND.getStatusText());
				return listPost;
			}
				
			for (Post post : list) {
				listPost.getListPost().add(post);
			}
			
			listPost.setStatusCode(Messages.SUCCESS.getStatusCode());
			listPost.setStatusText(Messages.SUCCESS.getStatusText());
			
			session.getTransaction().commit();
		
		} catch (Exception e) {
			
			if(e.getCause() instanceof ConstraintViolationException) {
				String msg = ((ConstraintViolationException) e.getCause()).getSQLException().getMessage();
				listPost.setStatusCode(Messages.CONSTRAINT_VIOLATION_EXCEPTION.getStatusCode());
				listPost.setStatusText(msg);
			
			} else {
				listPost.setStatusCode(Messages.ERROR_QUERYING_DATABASE.getStatusCode());
				listPost.setStatusText(e.getMessage());
			}
			
			Log log = LogFactory.getLog(PostDAO.class);
			log.error(e);
		}
		
		return listPost;
	}

}
