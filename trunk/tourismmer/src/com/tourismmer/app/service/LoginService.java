package com.tourismmer.app.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.tourismmer.app.dao.UserDAO;
import com.tourismmer.app.model.User;

@Path("/loginService")
public class LoginService {
	
	@POST
	@Path("/register")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public User register(User userParam) {

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
		dao.register(userParam);

		return userParam;
	
	}

}
