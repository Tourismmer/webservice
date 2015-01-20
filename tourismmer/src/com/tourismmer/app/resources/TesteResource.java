package com.tourismmer.app.resources;

import java.util.Calendar;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.tourismmer.app.constants.Labels;
import com.tourismmer.app.constants.Messages;
import com.tourismmer.app.constants.ViewConstants;
import com.tourismmer.app.dao.GroupDAO;
import com.tourismmer.app.dao.UserDAO;
import com.tourismmer.app.model.Group;
import com.tourismmer.app.model.Purpose;
import com.tourismmer.app.model.User;
import com.tourismmer.app.util.EncryptDecryptRSA;
import com.tourismmer.app.util.Util;

@Path("/teste")
public class TesteResource {
	
//	@POST
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response create(String param) {
//		System.out.println("teste");
//		return Response.status(200).entity("teste").build();
//	}
//	
//	@GET
//	@Path("/{id}")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public User getUser(@PathParam(Labels.ID) Long id) {
//		
//		User user = new User();
//		user.setId(id);
//
//		return user;
//	}
//	
//	@GET
//	@Path("/createGrupo")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public String getUser() {
//		
//		User user = new User();
//		user.setId(1L);
//		
//		Group trip = new Group();
//		trip.setOwner(user);
//		trip.setUserList(new ArrayList<User>());
//		trip.getUserList().add(user);
//		
//		GroupDAO dao = new GroupDAO();
//		dao.create(trip);
//
//		return "Creou";
//	}
	
	@GET 
	@Path("/testCreateUser")
	@Produces(MediaType.APPLICATION_JSON)
	public Response testCreateUser() {
		
		User userParam = new User();
		userParam.setName("Flavio");
		userParam.setEmail("flavioso16@gmail.com");
		userParam.setBirthday(Calendar.getInstance());
		userParam.setPass("a");
		
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
	@Path("/testUpdateUser")
	@Produces(MediaType.APPLICATION_JSON)
	public User testUpdateUser() {
		
		User userParam = new User();
		userParam.setId(1L);
		userParam.setName("Flavio Oliveira");
		userParam.setEmail("flavioso16@gmail.com");
		userParam.setBirthday(Calendar.getInstance());
		userParam.setPass("a");

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

		if(Util.isNotEmptyOrNull(invalidFields)) {
			userParam.setStatusCode(Messages.PARAMETERS_REQUIRED.getStatusCode());
			userParam.setStatusText(Messages.PARAMETERS_REQUIRED.getStatusText());
			return userParam;
		}
		
		UserDAO dao = new UserDAO();
//		userParam = dao.update(userParam);

		return userParam;
	}
	
	@GET
	@Path("/testCreateGroup")
	@Produces(MediaType.APPLICATION_JSON)
	public Response testCreateGroup() {
		
		Group groupParam = new Group();
		
		groupParam.setDestination("New York - USA");
		groupParam.setPurpose(new Purpose(1, "Passeio"));
		groupParam.setOwner(new User(1L));
		
		String invalidFields = null;
		Object[] fields = null;
		String[] labels = null;
		
		fields = new Object[]{groupParam.getDestination(), groupParam.getPurpose(), groupParam.getOwner().getId()};
		labels = new String[]{Labels.DESTINATION, Labels.PURPOSE, Labels.ID_USER};
		
		invalidFields = Util.validateParametersRequired(fields, labels);
		
		if(Util.isEmptyOrNull(invalidFields)) {
			GroupDAO dao = new GroupDAO();
			groupParam.setImage(dao.getImageGroup());
			groupParam = dao.create(groupParam);
			
		} else {
			groupParam.setStatusCode(Messages.PARAMETERS_REQUIRED.getStatusCode());
			groupParam.setStatusText(Messages.PARAMETERS_REQUIRED.getStatusText() + ViewConstants.COLON_SPACE + invalidFields);
		}

		return Response.status(200).entity(groupParam).build();
	}
	
	@GET
	@Path("/testeUpdateGroup")
	@Produces(MediaType.APPLICATION_JSON)
	public Group testeUpdateGroup() {
		
		Group groupParam = new Group();
		
		groupParam.setId(1L);
		groupParam.setDestination("São Paulo - Brasil");
		groupParam.setPurpose(new Purpose(2, "Estudos"));
		
		String invalidFields = null;
		Object[] fields = null;
		String[] labels = null;
		
		fields = new Object[]{groupParam.getDestination(), groupParam.getPurpose()};
		labels = new String[]{Labels.DESTINATION, Labels.PURPOSE};
		
		invalidFields = Util.validateParametersRequired(fields, labels);
		
		for(User u: groupParam.getUserList()) {
			if(Util.isEmptyOrNullOrZero(u.getId())) {
				if(Util.isNotEmptyOrNull(invalidFields)) invalidFields += ViewConstants.COMMA_SPACE;
				invalidFields += Labels.ID_USER;
			}
		}
		
		if(Util.isEmptyOrNull(invalidFields)) {
			GroupDAO dao = new GroupDAO();
			groupParam = dao.update(groupParam);
			
		} else {
			groupParam.setStatusCode(Messages.PARAMETERS_REQUIRED.getStatusCode());
			groupParam.setStatusText(Messages.PARAMETERS_REQUIRED.getStatusText() + ViewConstants.COLON_SPACE + invalidFields);
		}
		
		return groupParam;
	}

}
