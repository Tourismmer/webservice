package com.tourismmer.app.resources;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.tourismmer.app.constants.Labels;
import com.tourismmer.app.dao.GroupDAO;
import com.tourismmer.app.model.Group;
import com.tourismmer.app.model.User;

@Path("/teste")
public class TesteResource {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(String param) {
		System.out.println("teste");
		return Response.status(200).entity("teste").build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public User getUser(@PathParam(Labels.ID) Long id) {
		
		User user = new User();
		user.setId(id);

		return user;
	}
	
	@GET
	@Path("/createGrupo")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String getUser() {
		
		User user = new User();
		user.setId(1L);
		
		Group trip = new Group();
		trip.setUser(user);
		trip.setUserList(new ArrayList<User>());
		trip.getUserList().add(user);
		
		GroupDAO dao = new GroupDAO();
		dao.create(trip);

		return "Creou";
	}

}
