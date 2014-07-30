package com.tourismmer.app.service;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.tourismmer.app.Track;
import com.tourismmer.app.model.User;

@Path("/loginService")
public class LoginService {
	
	@GET
	@Path("/register")
	@Produces(MediaType.APPLICATION_JSON)
	public User register() {

		User user = new User();
		user.setStatusCode("1");
		user.setStatusText("Cadastro efetuado com sucesso!");
		user.setName("Flavio");
		user.setCity("Carapicuiba");
		user.setBirthday(new Date());
		user.setEmail("flavioso16@gmail.co,");
		user.setFacebookId("1");
		user.setGender("male");
		user.setPass("123");
		user.setRelationshipStatus("In a relationship");

		return user;
	
	}


}
