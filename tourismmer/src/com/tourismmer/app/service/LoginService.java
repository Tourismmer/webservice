package com.tourismmer.app.service;

import java.util.Calendar;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tourismmer.app.constants.Messages;
import com.tourismmer.app.dao.UserDAO;
import com.tourismmer.app.model.User;
import com.tourismmer.app.util.Util;

@Path("/loginService")
public class LoginService {
	
	@GET
	@Path("/register2")
	@Produces(MediaType.APPLICATION_JSON)
	public User register2() {
		
		User user = new User();
		user.setStatusCode("1");
		user.setStatusText("Cadastro efetuado com sucesso!");
		user.setName("12345");
		user.setCity("Carapicuiba");
		user.setBirthday(Calendar.getInstance());
		user.setEmail("flavioso16@gmail.com");
		user.setFacebookId("1");
		user.setGender("male");
		user.setPass("123");
		user.setRelationshipStatus("In a relationship");
		
		Log log = LogFactory.getLog(LoginService.class);
		log.info("teste");
		
		UserDAO dao = new UserDAO();
		user = dao.register(user);

		return user;
	}
	
	@POST
	@Path("/register")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response register(User userParam) {
		
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

//		User user = new User();
//		user.setStatusCode("1");
//		user.setStatusText("Cadastro efetuado com sucesso!");
//		user.setName("Flavio");
//		user.setCity("Carapicuiba");
//		user.setBirthday(Calendar.getInstance());
//		user.setEmail("flavioso16@gmail.com");
//		user.setFacebookId("1");
//		user.setGender("male");
//		user.setPass("123");
//		user.setRelationshipStatus("In a relationship");

		return Response.status(200).entity(userParam).build();
	
	}
	
	@GET
	@Path("/login/{email}/{pass}/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public User login(@PathParam("email") String email, @PathParam("pass") String pass) {
		
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

}
