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
import com.tourismmer.app.dao.CommentDAO;
import com.tourismmer.app.model.Comment;
import com.tourismmer.app.util.Util;

@Path("/comment")
public class CommentResource {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(Comment commentParam) {
		
		String invalidFields = null;
		Object[] fields = null;
		String[] labels = null;
		
		fields = new Object[]{commentParam.getDescription(), commentParam.getAuthor().getId(), commentParam.getPost().getId()};
		labels = new String[]{Labels.DESTINATION, Labels.ID_AUTHOR, Labels.ID_POST};
		
		invalidFields = Util.validateParametersRequired(fields, labels);
		
		if(Util.isEmptyOrNull(invalidFields)) {
			CommentDAO dao = new CommentDAO();
			commentParam = dao.create(commentParam);
			
		} else {
			commentParam.setStatusCode(Messages.PARAMETERS_REQUIRED.getStatusCode());
			commentParam.setStatusText(Messages.PARAMETERS_REQUIRED.getStatusText() + ViewConstants.COLON_SPACE + invalidFields);
		}

		return Response.status(200).entity(commentParam).build();
	}

}
