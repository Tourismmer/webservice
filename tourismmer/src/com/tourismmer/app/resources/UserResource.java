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

import com.tourismmer.app.constants.Messages;
import com.tourismmer.app.dao.UserDAO;
import com.tourismmer.app.model.User;
import com.tourismmer.app.util.Util;

@Path("/users")
public class UserResource {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(User userParam) {
		
		try {
		
			Object[] campos = {userParam, userParam.getName(), userParam.getCity(), userParam.getEmail(),
					userParam.getPass(), userParam.getFacebookId(), userParam.getBirthday()};
			
			if(Util.validateParametersRequired(campos)) {
				userParam.setStatusCode(Messages.PARAMETERS_REQUIRED.getStatusCode());
				userParam.setStatusText(Messages.PARAMETERS_REQUIRED.getStatusText());
				
			} else {
				UserDAO dao = new UserDAO();
				userParam = dao.register(userParam);
			}
			
		} catch (Exception e) {
			userParam.setStatusText(e.getMessage() + " - " + e.getStackTrace().toString());
		}

		return Response.status(200).entity(userParam).build();
	
	}
	
	@GET
	@Path("/{email}/{pass}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public User read(@PathParam("email") String email, @PathParam("pass") String pass) {
		
		User user = new User();
		user.setEmail(email);
		user.setPass(pass);
		
		Object[] campos = {user.getEmail(), user.getPass()};
		
		if(Util.validateParametersRequired(campos)) {
			user.setStatusCode(Messages.PARAMETERS_REQUIRED.getStatusCode());
			user.setStatusText(Messages.PARAMETERS_REQUIRED.getStatusText());
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

		Object[] campos = {userParam, userParam.getId(), userParam.getName(), userParam.getCity(), userParam.getEmail(),
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
	
}
