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
import com.tourismmer.app.dao.ImageDAO;
import com.tourismmer.app.model.Group;
import com.tourismmer.app.model.ListGroup;
import com.tourismmer.app.model.User;
import com.tourismmer.app.util.Util;

@Path("/group")
public class GroupResource {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(Group groupParam) {
		
		String invalidFields = null;
		Object[] fields = null;
		String[] labels = null;
		
		fields = new Object[]{groupParam.getDestination(), groupParam.getPurpose(), groupParam.getOwner().getId()};
		labels = new String[]{Labels.DESTINATION, Labels.PURPOSE, Labels.ID_USER};
		
		invalidFields = Util.validateParametersRequired(fields, labels);
		
		if(Util.isEmptyOrNull(invalidFields)) {
			GroupDAO groupDAO = new GroupDAO();
			ImageDAO imageDAO = new ImageDAO();
			groupParam.setImage(imageDAO.getImageRandom());
			groupParam.getUserList().add(new User(groupParam.getOwner().getId()));
			groupParam = groupDAO.create(groupParam);
			
		} else {
			groupParam.setStatusCode(Messages.PARAMETERS_REQUIRED.getStatusCode());
			groupParam.setStatusText(Messages.PARAMETERS_REQUIRED.getStatusText() + ViewConstants.COLON_SPACE + invalidFields);
		}

		return Response.status(200).entity(groupParam).build();
	}
	
//	@GET
//	@Path("/{id}")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Group getGroup(@PathParam(Labels.ID) Long id) {
//		
//		Group group = new Group();
//		group.setId(id);
//		
//		if(Util.isEmptyOrNullOrZero(id)) {
//			group.setStatusCode(Messages.PARAMETERS_REQUIRED.getStatusCode());
//			group.setStatusText(Messages.PARAMETERS_REQUIRED.getStatusText() +  ViewConstants.COLON_SPACE + Labels.ID);
//			return group;
//		}
//		
//		GroupDAO dao = new GroupDAO();
//		group = dao.getGroup(group);
//
//		return group;
//	}
//	
//	@PUT
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Group update(Group groupParam) {
//		
//		String invalidFields = null;
//		Object[] fields = null;
//		String[] labels = null;
//		
//		fields = new Object[]{groupParam.getDestination(), groupParam.getPurpose()};
//		labels = new String[]{Labels.DESTINATION, Labels.PURPOSE};
//		
//		invalidFields = Util.validateParametersRequired(fields, labels);
//		
//		for(User u: groupParam.getUserList()) {
//			if(Util.isEmptyOrNullOrZero(u.getId())) {
//				if(Util.isNotEmptyOrNull(invalidFields)) invalidFields += ViewConstants.COMMA_SPACE;
//				invalidFields += Labels.ID_USER;
//			}
//		}
//		
//		if(Util.isEmptyOrNull(invalidFields)) {
//			GroupDAO dao = new GroupDAO();
//			groupParam = dao.update(groupParam);
//			
//		} else {
//			groupParam.setStatusCode(Messages.PARAMETERS_REQUIRED.getStatusCode());
//			groupParam.setStatusText(Messages.PARAMETERS_REQUIRED.getStatusText() + ViewConstants.COLON_SPACE + invalidFields);
//		}
//		
//		return groupParam;
//	}
	
	@GET
	@Path("/getTopGroups/{amount}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ListGroup getTopGroups(@PathParam(Labels.AMOUNT) Integer amount) {
		
		ListGroup listGroup = new ListGroup();
		
		if(Util.isEmptyOrNullOrZero(amount)) {
			listGroup.setStatusCode(Messages.PARAMETERS_REQUIRED.getStatusCode());
			listGroup.setStatusText(Messages.PARAMETERS_REQUIRED.getStatusText() +  ViewConstants.COLON_SPACE + Labels.AMOUNT);
			return listGroup;
		}
		
		GroupDAO dao = new GroupDAO();
		listGroup = dao.getTopTrips(amount);

		return listGroup;
	}
	
	@GET
	@Path("/getUserGroups/{idUser}/{amount}/{firstResult}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ListGroup getUserGroups(@PathParam("idUser") Long idUser, @PathParam(Labels.AMOUNT) Integer amount, @PathParam(Labels.FIRST_RESULT) Integer firstResult) {
		
		ListGroup listGroup = new ListGroup();
		
		String invalidFields = null;
		Object[] fields = null;
		String[] labels = null;
		
			fields = new Object[]{idUser, amount};
			labels = new String[]{Labels.ID, Labels.AMOUNT};
			
		invalidFields = Util.validateParametersRequired(fields, labels);
		
		if(Util.isEmptyOrNull(invalidFields)) {
			GroupDAO dao = new GroupDAO();
			listGroup = dao.getUserGroups(idUser, amount, firstResult);
			
		} else {
			listGroup.setStatusCode(Messages.PARAMETERS_REQUIRED.getStatusCode());
			listGroup.setStatusText(Messages.PARAMETERS_REQUIRED.getStatusText() + ViewConstants.COLON_SPACE + invalidFields);
		}
		

		return listGroup;
	}
	
	

}
