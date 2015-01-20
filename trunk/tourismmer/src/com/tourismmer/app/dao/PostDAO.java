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
import com.tourismmer.app.model.ListPost;
import com.tourismmer.app.model.Post;
import com.tourismmer.app.util.Util;

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
			Log log = LogFactory.getLog(PostDAO.class);
			log.error(e);
		}
		
		return postParam;
		
	}
	
	public Post getPost(Post postParam) {
		
		try {
			
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("Post");
			EntityManager manager = factory.createEntityManager();
			
			Query query = manager.createQuery("select p from Post as p where p.id = ?");
			query.setParameter(1, Util.getLong(postParam.getId()));
			
			@SuppressWarnings("unchecked")
			List<Post> list = query.getResultList();
			
			if(list.size()>0) {
				
				postParam = list.get(0);
				postParam.setStatusCode(Messages.SUCCESS.getStatusCode());
				postParam.setStatusText(Messages.SUCCESS.getStatusText());
					
			} else {
				postParam.setStatusCode(Messages.QUERY_NOT_FOUND.getStatusCode());
				postParam.setStatusText(Messages.QUERY_NOT_FOUND.getStatusText());
			}
			
			manager.close();
		
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
			
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("Post");
			EntityManager manager = factory.createEntityManager();
			
			
			Query query = manager.createQuery("Select p from Post p where p.author.id = ? and p.group.id = ?");
			query.setParameter(1, Util.getLong(idUser));
			query.setParameter(2, Util.getLong(idGroup));
			query.setFirstResult(firstResult);
			query.setMaxResults(amount);
			
			@SuppressWarnings("unchecked")
			List<Post> list = query.getResultList();
			
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
			
			manager.close();
		
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
