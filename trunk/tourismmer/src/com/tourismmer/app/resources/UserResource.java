package com.tourismmer.app.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.mail.EmailException;

import com.tourismmer.app.constants.ViewConstants;
import com.tourismmer.app.constants.Labels;
import com.tourismmer.app.constants.Messages;
import com.tourismmer.app.dao.UserDAO;
import com.tourismmer.app.model.Message;
import com.tourismmer.app.model.User;
import com.tourismmer.app.util.EmailService;
import com.tourismmer.app.util.EncryptDecryptRSA;
import com.tourismmer.app.util.Util;

@Path("/user")
public class UserResource {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(User userParam) {
		
		try {
			
			String invalidFields = null;
			Object[] fields = null;
			String[] labels = null;
			
			if(Util.isEmptyOrNullOrZero(userParam.getFacebookId())) {
				fields = new Object[]{userParam.getName(), userParam.getEmail(),
						userParam.getPass(), userParam.getBirthday()};
				labels = new String[]{Labels.NAME, Labels.EMAIL, Labels.PASS, Labels.BIRTHDAY};
				
			} else {
				fields = new Object[]{userParam.getName(), userParam.getEmail(),
						userParam.getFacebookId(), userParam.getBirthday()};
				labels = new String[]{Labels.NAME, Labels.EMAIL, Labels.FACEBOOKID, Labels.BIRTHDAY};
			}
			
			invalidFields = Util.validateParametersRequired(fields, labels);
			
			if(Util.isEmptyOrNull(invalidFields)) {
				UserDAO dao = new UserDAO();
				userParam.setPass(EncryptDecryptRSA.encrypt(userParam.getPass()));
				userParam = dao.create(userParam);
				
			} else {
				userParam.setStatusCode(Messages.PARAMETERS_REQUIRED.getStatusCode());
				userParam.setStatusText(Messages.PARAMETERS_REQUIRED.getStatusText() + ViewConstants.COLON_SPACE + invalidFields);
			}
			
		} catch (Exception e) {
			userParam.setStatusText(e.getMessage() + " - " + e.getStackTrace().toString());
		}
		
		userParam.setPass(EncryptDecryptRSA.decrypt(userParam.getPass()));

		return Response.status(200).entity(userParam).build();
	}
	
	@GET
	@Path("/facebook/{facebookId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public User getUserFacebook(@PathParam(Labels.FACEBOOKID) String facebookId) {
		
		User user = new User();
		user.setFacebookId(facebookId);
		
		if(Util.isEmptyOrNull(facebookId)) {
			user.setStatusCode(Messages.PARAMETERS_REQUIRED.getStatusCode());
			user.setStatusText(Messages.PARAMETERS_REQUIRED.getStatusText() +  ViewConstants.COLON_SPACE + Labels.FACEBOOKID);
			return user;
		}
		
		UserDAO dao = new UserDAO();
		user = dao.loginFacebook(user);

		return user;
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public User getUser(@PathParam(Labels.ID) Long id) {
		
		User user = new User();
		user.setId(id);
		
		if(Util.isEmptyOrNullOrZero(id)) {
			user.setStatusCode(Messages.PARAMETERS_REQUIRED.getStatusCode());
			user.setStatusText(Messages.PARAMETERS_REQUIRED.getStatusText() +  ViewConstants.COLON_SPACE + Labels.ID);
			return user;
		}
		
		UserDAO dao = new UserDAO();
		user = dao.getUser(user);

		return user;
	}
	
	@GET
	@Path("/{email}/{pass}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public User read(@PathParam(Labels.EMAIL) String email, @PathParam(Labels.PASS) String pass) {
		
		User user = new User();
		user.setEmail(email);
		user.setPass(pass);
		
		Object[] fields = {user.getEmail(), user.getPass()};
		String[] labels = {Labels.EMAIL, Labels.PASS};
		
		String invalidFields = Util.validateParametersRequired(fields, labels);
		
		if(!Util.isEmptyOrNull(invalidFields)) {
			user.setStatusCode(Messages.PARAMETERS_REQUIRED.getStatusCode());
			user.setStatusText(Messages.PARAMETERS_REQUIRED.getStatusText() + ViewConstants.COLON_SPACE + invalidFields);
			return user;
		}
		
		UserDAO dao = new UserDAO();
		user = dao.login(user);

		return user;
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public User update(User userParam) {

		Object[] campos = {userParam, userParam.getId(), userParam.getName(), userParam.getEmail(),
				userParam.getPass(), userParam.getFacebookId(), userParam.getBirthday()};
		
		if(Util.validateParametersRequired(campos)) {
			userParam.setStatusCode(Messages.PARAMETERS_REQUIRED.getStatusCode());
			userParam.setStatusText(Messages.PARAMETERS_REQUIRED.getStatusText());
			return userParam;
		}
		
		UserDAO dao = new UserDAO();
		userParam = dao.update(userParam);

		return userParam;
	}
	
	@GET
	@Path("/passRecover/{email}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Message passRecover(@PathParam(Labels.EMAIL) String email) {
		
		Message messageReturn = new Message();
		UserDAO dao = new UserDAO();
		User user = dao.getUserByEmail(email);
		
		if(Messages.SUCCESS.getStatusCode().equals(user.getStatusCode())) {
			
			try {
				EmailService.sendEmailPassRecover(user);
				messageReturn.setStatusCode(Messages.SUCCESS.getStatusCode());
				messageReturn.setStatusCode(Messages.SUCCESS.getStatusCode());
				
			} catch (EmailException e) {
				messageReturn.setStatusCode(Messages.ERROR_SEND_EMAIL.getStatusCode());
				messageReturn.setStatusCode(e.getMessage());
			}
			
		} else {
			messageReturn.setStatusCode(Messages.QUERY_NOT_FOUND.getStatusCode());
			messageReturn.setStatusCode(Messages.QUERY_NOT_FOUND.getStatusCode());
		}
		return messageReturn;
	}
	
	@PUT
	@Path("/changePass/{id}/{pass}")
	@Produces(MediaType.APPLICATION_JSON)
	public Message changePass(@PathParam(Labels.ID) String id, @PathParam(Labels.PASS) String pass) {
		
		Message messageReturn = new Message();
		User user = getUser(Util.getLong(EncryptDecryptRSA.decrypt(id)));
		
		if(Messages.SUCCESS.getStatusCode().equals(user.getStatusCode())) {
			
			UserDAO dao = new UserDAO();
			user.setPass(EncryptDecryptRSA.encrypt(pass));
			dao.update(user);
			messageReturn.setStatusCode(Messages.SUCCESS.getStatusCode());
			messageReturn.setStatusCode(Messages.SUCCESS.getStatusCode());
				
		} else {
			messageReturn.setStatusCode(Messages.QUERY_NOT_FOUND.getStatusCode());
			messageReturn.setStatusCode(Messages.QUERY_NOT_FOUND.getStatusCode());
		}
		return messageReturn;
	}
	
}
