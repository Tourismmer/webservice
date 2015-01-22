package com.tourismmer.app.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.tourismmer.app.dao.ImageDAO;
import com.tourismmer.app.model.Image;

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

}
