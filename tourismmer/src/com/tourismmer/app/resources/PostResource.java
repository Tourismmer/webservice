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
import com.tourismmer.app.dao.PostDAO;
import com.tourismmer.app.model.ListPost;
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
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Post getPost(@PathParam(Labels.ID) Long id) {
		
		Post post = new Post();
		post.setId(id);
		
		if(Util.isEmptyOrNullOrZero(id)) {
			post.setStatusCode(Messages.PARAMETERS_REQUIRED.getStatusCode());
			post.setStatusText(Messages.PARAMETERS_REQUIRED.getStatusText() +  ViewConstants.COLON_SPACE + Labels.ID);
			return post;
		}
		
		PostDAO dao = new PostDAO();
		post = dao.getPost(post);

		return post;
	}
	
	@GET
	@Path("/getListPost/{idUser}/{idGroup}/{amount}/{firstResult}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ListPost getListPost(@PathParam("idUser") Long idUser, @PathParam("idGroup") Long idGroup, @PathParam(Labels.AMOUNT) Integer amount, @PathParam(Labels.FIRST_RESULT) Integer firstResult) {
		
		ListPost listPost = new ListPost();
		
		String invalidFields = null;
		Object[] fields = null;
		String[] labels = null;
		
			fields = new Object[]{idUser, idGroup, amount};
			labels = new String[]{Labels.ID, Labels.ID_GROUP, Labels.AMOUNT};
			
		invalidFields = Util.validateParametersRequired(fields, labels);
		
		if(Util.isEmptyOrNull(invalidFields)) {
			PostDAO dao = new PostDAO();
			listPost = dao.getListPost(idUser, idGroup, amount, firstResult);
			
		} else {
			listPost.setStatusCode(Messages.PARAMETERS_REQUIRED.getStatusCode());
			listPost.setStatusText(Messages.PARAMETERS_REQUIRED.getStatusText() + ViewConstants.COLON_SPACE + invalidFields);
		}
		

		return listPost;
	}

}
