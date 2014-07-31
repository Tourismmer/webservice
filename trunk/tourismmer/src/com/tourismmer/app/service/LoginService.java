package com.tourismmer.app.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.tourismmer.app.constants.Messages;
import com.tourismmer.app.dao.UserDAO;
import com.tourismmer.app.model.User;
import com.tourismmer.app.util.Util;

@Path("/loginService")
public class LoginService {
	
	@POST
	@Path("/register")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public User register(User userParam) {
		
		Object[] campos = {userParam, userParam.getName(), userParam.getCity(), userParam.getEmail(),
				userParam.getPass(), userParam.getFacebookId(), userParam.getBirthday()};
		
		if(Util.validateParametersRequired(campos)) {
			userParam.setStatusCode(Messages.PARAMETERS_REQUIRED.getStatusCode());
			userParam.setStatusText(Messages.PARAMETERS_REQUIRED.getStatusText());
			return userParam;
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
		
		UserDAO dao = new UserDAO();
		userParam = dao.register(userParam);

		return userParam;
	
	}
	
	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public User login(User userParam) {
		
		Object[] campos = {userParam, userParam.getName(), userParam.getEmail()};
		
		if(Util.validateParametersRequired(campos)) {
			userParam.setStatusCode(Messages.PARAMETERS_REQUIRED.getStatusCode());
			userParam.setStatusText(Messages.PARAMETERS_REQUIRED.getStatusText());
			return userParam;
		}
		
		UserDAO dao = new UserDAO();
		userParam = dao.login(userParam);

		return userParam;
	
	}

}
