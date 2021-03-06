package com.tourismmer.app.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import com.tourismmer.app.constants.Messages;
import com.tourismmer.app.model.Image;
import com.tourismmer.app.model.ImageS3;
import com.tourismmer.app.util.EncryptDecryptRSA;
import com.tourismmer.app.util.HibernateUtil;
import com.tourismmer.app.util.Util;

public class ImageDAO {
	
	public Image getImageRandom() {
		
		Image imageReturn = new Image();
		
		try {
			
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			
			Query query = session.createQuery("from Image u order by rand()");
			
			@SuppressWarnings("rawtypes")
			List listImage = query.list();
			
			if(Util.isNotEmptyOrNull(listImage)) {
				
				Image image = (Image) listImage.get(0);
				imageReturn = image;
				imageReturn.setStatusCode(Messages.SUCCESS.getStatusCode());
				imageReturn.setStatusText(Messages.SUCCESS.getStatusText());
					
			} else {
				imageReturn.setStatusCode(Messages.QUERY_NOT_FOUND.getStatusCode());
				imageReturn.setStatusText(Messages.QUERY_NOT_FOUND.getStatusText());
			}
			
			session.getTransaction().commit();
			session.close();
		
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
	
	public Image create(Image imageParam) {
		
		try {
			
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			
			session.save(imageParam);
			
			imageParam.setStatusCode(Messages.SUCCESS.getStatusCode());
			imageParam.setStatusText(Messages.SUCCESS.getStatusText());
			
			session.getTransaction().commit();
			
			session.close();
		
		} catch (Exception e) {
			
			if(e.getCause() instanceof ConstraintViolationException) {
				String msg = ((ConstraintViolationException) e.getCause()).getSQLException().getMessage();
				imageParam.setStatusCode(Messages.CONSTRAINT_VIOLATION_EXCEPTION.getStatusCode());
				imageParam.setStatusText(msg);
			
			} else {
				imageParam.setStatusCode(Messages.ERROR_QUERYING_DATABASE.getStatusCode());
				imageParam.setStatusText(e.getMessage());
			}
			
			Log log = LogFactory.getLog(UserDAO.class);
			log.error(e);
		}
		
		return imageParam;
		
	}
	
	public ImageS3 getIdS3Randown() {
		ImageS3 image = new ImageS3();
		try {
			
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			
			session.save(image);
			
			image.setSequence(EncryptDecryptRSA.encrypt(image.getId().toString()));
			
			image.setStatusCode(Messages.SUCCESS.getStatusCode());
			image.setStatusText(Messages.SUCCESS.getStatusText());
			
			session.getTransaction().commit();
			
			session.close();
		
		} catch (Exception e) {
			
			if(e.getCause() instanceof ConstraintViolationException) {
				String msg = ((ConstraintViolationException) e.getCause()).getSQLException().getMessage();
				image.setStatusCode(Messages.CONSTRAINT_VIOLATION_EXCEPTION.getStatusCode());
				image.setStatusText(msg);
			
			} else {
				image.setStatusCode(Messages.ERROR_QUERYING_DATABASE.getStatusCode());
				image.setStatusText(e.getMessage());
			}
			
			Log log = LogFactory.getLog(UserDAO.class);
			log.error(e);
		}
		
		return image;
		
	}

}
