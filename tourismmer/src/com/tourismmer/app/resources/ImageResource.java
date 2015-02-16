package com.tourismmer.app.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.tourismmer.app.constants.Labels;
import com.tourismmer.app.constants.Messages;
import com.tourismmer.app.constants.ViewConstants;
import com.tourismmer.app.dao.ImageDAO;
import com.tourismmer.app.model.Image;
import com.tourismmer.app.util.Util;

@Path("/image")
public class ImageResource {
	
	
	@GET
	@Path("/getImageRandom")
	@Produces(MediaType.APPLICATION_JSON)
	public Image getImageRandom() {
		
		Image image = new Image();
		ImageDAO dao = new ImageDAO();
		image = dao.getImageRandom();

		return image;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(Image imageParam) {
		
		try {
			
			String invalidFields = null;
			Object[] fields = null;
			String[] labels = null;
			
			fields = new Object[]{imageParam.getUrl(), imageParam.getOwner(), imageParam.getOwner().getId()};
			labels = new String[]{Labels.URL, Labels.OWNER, Labels.ID_OWNER};
			
			invalidFields = Util.validateParametersRequired(fields, labels);
			
			if(Util.isEmptyOrNull(invalidFields)) {
				ImageDAO dao = new ImageDAO();
				imageParam = dao.create(imageParam);
				
			} else {
				imageParam.setStatusCode(Messages.PARAMETERS_REQUIRED.getStatusCode());
				imageParam.setStatusText(Messages.PARAMETERS_REQUIRED.getStatusText() + ViewConstants.COLON_SPACE + invalidFields);
			}
			
		} catch (Exception e) {
			imageParam.setStatusText(e.getMessage() + " - " + e.getStackTrace().toString());
		}

		return Response.status(200).entity(imageParam).build();
	}
	

}
