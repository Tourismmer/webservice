package com.tourismmer.app.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.tourismmer.app.dao.TripDAO;
import com.tourismmer.app.model.Trip;

@Path("/trip")
public class TripResource {
	
//	@POST
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response create(Trip tripParam) {
//		
//		try {
//			
//			String invalidFields = null;
//			Object[] fields = null;
//			String[] labels = null;
//			
//			fields = new Object[]{tripParam.getDestination(), tripParam.getPurpose(), tripParam.getUser().getId()};
//			labels = new String[]{Labels.DESTINATION, Labels.PURPOSE, Labels.ID_USER};
//			
//			invalidFields = Util.validateParametersRequired(fields, labels);
//			
//			if(Util.isEmptyOrNull(invalidFields)) {
//				TripDAO dao = new TripDAO();
//				tripParam = dao.create(tripParam);
//				
//			} else {
//				tripParam.setStatusCode(Messages.PARAMETERS_REQUIRED.getStatusCode());
//				tripParam.setStatusText(Messages.PARAMETERS_REQUIRED.getStatusText() + Constants.COLON_SPACE + invalidFields);
//			}
//			
//		} catch (Exception e) {
//			tripParam.setStatusText(e.getMessage() + " - " + e.getStackTrace().toString());
//		}
//
//		return Response.status(200).entity(tripParam).build();
//	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Trip get() {
		
		Trip trip = new Trip();
		trip.setDestination("Orlando");
		trip.setPurpose("Tour");
		trip.getUser().setId(2L);
		
		TripDAO dao = new TripDAO();
		dao.create(trip);

		return trip;
	}

}
