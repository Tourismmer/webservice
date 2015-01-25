package com.tourismmer.app.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import com.tourismmer.app.constants.Messages;
import com.tourismmer.app.model.Comment;
import com.tourismmer.app.model.ListComment;
import com.tourismmer.app.util.HibernateUtil;
import com.tourismmer.app.util.Util;

public class CommentDAO {
	
	public Comment create(Comment commentParam) {
		
		try {
			
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			
			session.save(commentParam);
			
			commentParam.setStatusCode(Messages.SUCCESS.getStatusCode());
			commentParam.setStatusText(Messages.SUCCESS.getStatusText());
			
			session.getTransaction().commit();
		
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
			
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			
			Comment comment = (Comment) session.get(Comment.class, commentParam.getId());
			
			if(comment != null) {
				
				commentParam.setId(comment.getId());
				commentParam.setDescription(comment.getDescription());
				commentParam.setAuthor(comment.getAuthor());
				
				commentParam.setStatusCode(Messages.SUCCESS.getStatusCode());
				commentParam.setStatusText(Messages.SUCCESS.getStatusText());
					
			} else {
				commentParam.setStatusCode(Messages.QUERY_NOT_FOUND.getStatusCode());
				commentParam.setStatusText(Messages.QUERY_NOT_FOUND.getStatusText());
			}
			
			session.getTransaction().commit();
		
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
			
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			
			Query query = session.createQuery("from Comment p where p.author.id = :idUser and p.post.id = :idPost");
			query.setParameter("idUser", Util.getLong(idUser));
			query.setParameter("idPost", Util.getLong(idPost));
			query.setMaxResults(amount);
			query.setFirstResult(firstResult);
			
			@SuppressWarnings("unchecked")
			List<Comment> list = query.list();
			
			if(Util.isEmptyOrNull(list)) {
				listComment.setStatusCode(Messages.QUERY_NOT_FOUND.getStatusCode());
				listComment.setStatusText(Messages.QUERY_NOT_FOUND.getStatusText());
				return listComment;
			}
			
			Comment comment = null;
				
			for (Comment c : list) {
				comment = new Comment();
				comment.setId(c.getId());
				comment.setDescription(c.getDescription());
				comment.setAuthor(c.getAuthor());
				listComment.getListComment().add(comment);
			}
			
			listComment.setStatusCode(Messages.SUCCESS.getStatusCode());
			listComment.setStatusText(Messages.SUCCESS.getStatusText());
				
			
			session.getTransaction().commit();
		
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
