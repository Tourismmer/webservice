package com.tourismmer.app.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.tourismmer.app.constants.Labels;
import com.tourismmer.app.constants.Messages;
import com.tourismmer.app.constants.ViewConstants;
import com.tourismmer.app.dao.GroupDAO;
import com.tourismmer.app.model.Group;
import com.tourismmer.app.util.Util;

@Path("/group")
public class GroupResource {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(Group groupParam) {
		
		try {
			
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
			
		} catch (Exception e) {
			groupParam.setStatusText(e.getMessage() + " - " + e.getStackTrace().toString());
		}

		return Response.status(200).entity(groupParam).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Group getGroup(@PathParam(Labels.ID) Long id) {
		
		Group group = new Group();
		group.setId(id);
		
		if(Util.isEmptyOrNullOrZero(id)) {
			group.setStatusCode(Messages.PARAMETERS_REQUIRED.getStatusCode());
			group.setStatusText(Messages.PARAMETERS_REQUIRED.getStatusText() +  ViewConstants.COLON_SPACE + Labels.ID);
			return group;
		}
		
		GroupDAO dao = new GroupDAO();
		group = dao.getGroup(group);

		return group;
	}

}
