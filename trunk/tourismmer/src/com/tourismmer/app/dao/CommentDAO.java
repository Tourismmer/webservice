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
import com.tourismmer.app.model.Comment;
import com.tourismmer.app.model.ListComment;
import com.tourismmer.app.util.Util;

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
			Log log = LogFactory.getLog(CommentDAO.class);
			log.error(e);
		}
		
		return commentParam;
		
	}
	
	public Comment getComment(Comment commentParam) {
		
		try {
			
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("Comment");
			EntityManager manager = factory.createEntityManager();
			
			Query query = manager.createQuery("select c from Comment as c where c.id = ?");
			query.setParameter(1, Util.getLong(commentParam.getId()));
			
			@SuppressWarnings("unchecked")
			List<Comment> list = query.getResultList();
			
			if(list.size()>0) {
				
				commentParam = list.get(0);
				commentParam.setStatusCode(Messages.SUCCESS.getStatusCode());
				commentParam.setStatusText(Messages.SUCCESS.getStatusText());
					
			} else {
				commentParam.setStatusCode(Messages.QUERY_NOT_FOUND.getStatusCode());
				commentParam.setStatusText(Messages.QUERY_NOT_FOUND.getStatusText());
			}
			
			manager.close();
		
		} catch (Exception e) {
			
			if(e.getCause() instanceof ConstraintViolationException) {
				String msg = ((ConstraintViolationException) e.getCause()).getSQLException().getMessage();
				commentParam.setStatusCode(Messages.CONSTRAINT_VIOLATION_EXCEPTION.getStatusCode());
				commentParam.setStatusText(msg);
			
			} else {
				commentParam.setStatusCode(Messages.ERROR_QUERYING_DATABASE.getStatusCode());
				commentParam.setStatusText(e.getMessage());
			}
			
			Log log = LogFactory.getLog(CommentDAO.class);
			log.error(e);
		}
		
		return commentParam;
		
	}
	
	public ListComment getListComment(Long idUser, Long idPost, Integer amount, Integer firstResult) {
		
		ListComment listComment = new ListComment();
		
		try {
			
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("Comment");
			EntityManager manager = factory.createEntityManager();
			
			
			Query query = manager.createQuery("Select c from Comment c where c.author.id = ? and c.post.id = ?");
			query.setParameter(1, Util.getLong(idUser));
			query.setParameter(2, Util.getLong(idPost));
			query.setFirstResult(firstResult);
			query.setMaxResults(amount);
			
			@SuppressWarnings("unchecked")
			List<Comment> list = query.getResultList();
			
			if(Util.isEmptyOrNull(list)) {
				listComment.setStatusCode(Messages.QUERY_NOT_FOUND.getStatusCode());
				listComment.setStatusText(Messages.QUERY_NOT_FOUND.getStatusText());
				return listComment;
			}
				
			for (Comment c : list) {
				listComment.getListComment().add(c);
			}
			
			listComment.setStatusCode(Messages.SUCCESS.getStatusCode());
			listComment.setStatusText(Messages.SUCCESS.getStatusText());
				
			
			manager.close();
		
		} catch (Exception e) {
			
			if(e.getCause() instanceof ConstraintViolationException) {
				String msg = ((ConstraintViolationException) e.getCause()).getSQLException().getMessage();
				listComment.setStatusCode(Messages.CONSTRAINT_VIOLATION_EXCEPTION.getStatusCode());
				listComment.setStatusText(msg);
			
			} else {
				listComment.setStatusCode(Messages.ERROR_QUERYING_DATABASE.getStatusCode());
				listComment.setStatusText(e.getMessage());
			}
			
			Log log = LogFactory.getLog(GroupDAO.class);
			log.error(e);
		}
		
		return listComment;
	}

}
