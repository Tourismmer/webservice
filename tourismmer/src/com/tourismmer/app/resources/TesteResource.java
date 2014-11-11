package com.tourismmer.app.resources;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.tourismmer.app.constants.Labels;
import com.tourismmer.app.dao.TripDAO;
import com.tourismmer.app.model.Trip;
import com.tourismmer.app.model.User;

@Path("/teste")
public class TesteResource {
	
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
		
		Trip trip = new Trip();
		trip.setUser(user);
		trip.setUserList(new ArrayList<User>());
		trip.getUserList().add(user);
		
		TripDAO dao = new TripDAO();
		dao.create(trip);

		return "Creou";
	}

}
