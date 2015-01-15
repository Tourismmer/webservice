package com.tourismmer.app.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.tourismmer.app.constants.Labels;
import com.tourismmer.app.constants.Messages;
import com.tourismmer.app.constants.ViewConstants;
import com.tourismmer.app.dao.PostDAO;
import com.tourismmer.app.model.Post;
import com.tourismmer.app.util.Util;

@Path("/post")
public class PostResource {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(Post postParam) {
		
		String invalidFields = null;
		Object[] fields = null;
		String[] labels = null;
		
		fields = new Object[]{postParam.getDescription(), postParam.getAuthor().getId(), postParam.getGroup().getId()};
		labels = new String[]{Labels.DESTINATION, Labels.ID_AUTHOR, Labels.ID_GROUP};
		
		invalidFields = Util.validateParametersRequired(fields, labels);
		
		if(Util.isEmptyOrNull(invalidFields)) {
			PostDAO dao = new PostDAO();
			postParam = dao.create(postParam);
			
		} else {
			postParam.setStatusCode(Messages.PARAMETERS_REQUIRED.getStatusCode());
			postParam.setStatusText(Messages.PARAMETERS_REQUIRED.getStatusText() + ViewConstants.COLON_SPACE + invalidFields);
		}

		return Response.status(200).entity(postParam).build();
	}

}
