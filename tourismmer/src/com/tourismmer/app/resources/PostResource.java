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
import com.tourismmer.app.model.LikePost;
import com.tourismmer.app.model.ListPost;
import com.tourismmer.app.model.Post;
import com.tourismmer.app.model.User;
import com.tourismmer.app.model.UserGo;
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
		
		fields = new Object[]{postParam.getDescription(), postParam.getAuthor().getId(), postParam.getGroup().getId(), postParam.getTypePost()};
		labels = new String[]{Labels.DESTINATION, Labels.ID_AUTHOR, Labels.ID_GROUP, Labels.TYPE_POST};
		
		invalidFields = Util.validateParametersRequired(fields, labels);
		
		if(Util.isEmptyOrNull(invalidFields)) {
			PostDAO postDAO = new PostDAO();
			postParam.getUserList().add(new User(postParam.getAuthor().getId()));
			postParam = postDAO.create(postParam);
			
		} else {
			postParam.setStatusCode(Messages.PARAMETERS_REQUIRED.getStatusCode());
			postParam.setStatusText(Messages.PARAMETERS_REQUIRED.getStatusText() + ViewConstants.COLON_SPACE + invalidFields);
		}

		return Response.status(200).entity(postParam).build();
	}
	
	@POST
	@Path("/userGo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response join(UserGo likeParam) {
		
		String invalidFields = null;
		Object[] fields = null;
		String[] labels = null;
		
		fields = new Object[]{likeParam.getIdUser(), likeParam.getIdPost()};
		labels = new String[]{Labels.ID_USER, Labels.ID_POST};
		
		invalidFields = Util.validateParametersRequired(fields, labels);
		
		if(Util.isEmptyOrNull(invalidFields)) {
			PostDAO postDAO = new PostDAO();
			likeParam = postDAO.join(likeParam);
			
		} else {
			likeParam.setStatusCode(Messages.PARAMETERS_REQUIRED.getStatusCode());
			likeParam.setStatusText(Messages.PARAMETERS_REQUIRED.getStatusText() + ViewConstants.COLON_SPACE + invalidFields);
		}

		return Response.status(200).entity(likeParam).build();
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
	@Path("/getListPost/{idGroup}/{idUser}/{amount}/{firstResult}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ListPost getListPost(@PathParam("idGroup") Long idGroup, @PathParam("idUser") Long idUser, @PathParam(Labels.AMOUNT) Integer amount, @PathParam(Labels.FIRST_RESULT) Integer firstResult) {
		
		ListPost listPost = new ListPost();
		
		String invalidFields = null;
		Object[] fields = null;
		String[] labels = null;
		
			fields = new Object[]{idGroup, amount};
			labels = new String[]{Labels.ID_GROUP, Labels.AMOUNT};
			
		invalidFields = Util.validateParametersRequired(fields, labels);
		
		if(Util.isEmptyOrNull(invalidFields)) {
			PostDAO dao = new PostDAO();
			listPost = dao.getListPost(idGroup, amount, firstResult, idUser);
			
		} else {
			listPost.setStatusCode(Messages.PARAMETERS_REQUIRED.getStatusCode());
			listPost.setStatusText(Messages.PARAMETERS_REQUIRED.getStatusText() + ViewConstants.COLON_SPACE + invalidFields);
		}
		

		return listPost;
	}
	
	@POST
	@Path("/like")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response like(LikePost likeParam) {
		
		String invalidFields = null;
		Object[] fields = null;
		String[] labels = null;
		
		fields = new Object[]{likeParam.getIdUser(), likeParam.getIdPost()};
		labels = new String[]{Labels.ID_USER, Labels.ID_POST};
		
		invalidFields = Util.validateParametersRequired(fields, labels);
		
		if(Util.isEmptyOrNull(invalidFields)) {
			PostDAO postDAO = new PostDAO();
			likeParam = postDAO.like(likeParam);
			
		} else {
			likeParam.setStatusCode(Messages.PARAMETERS_REQUIRED.getStatusCode());
			likeParam.setStatusText(Messages.PARAMETERS_REQUIRED.getStatusText() + ViewConstants.COLON_SPACE + invalidFields);
		}

		return Response.status(200).entity(likeParam).build();
	}
	

}
