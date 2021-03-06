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
import com.tourismmer.app.dao.CommentDAO;
import com.tourismmer.app.model.Comment;
import com.tourismmer.app.model.LikeComment;
import com.tourismmer.app.model.ListComment;
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
		labels = new String[]{Labels.DESCRIPTION, Labels.ID_AUTHOR, Labels.ID_POST};
		
		invalidFields = Util.validateParametersRequired(fields, labels);
		
		if(Util.isEmptyOrNull(invalidFields)) {
			CommentDAO commentDAO = new CommentDAO();
			commentParam = commentDAO.create(commentParam);
			
		} else {
			commentParam.setStatusCode(Messages.PARAMETERS_REQUIRED.getStatusCode());
			commentParam.setStatusText(Messages.PARAMETERS_REQUIRED.getStatusText() + ViewConstants.COLON_SPACE + invalidFields);
		}

		return Response.status(200).entity(commentParam).build();
	}
	
	@POST
	@Path("/like")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response like(LikeComment likeParam) {
		
		String invalidFields = null;
		Object[] fields = null;
		String[] labels = null;
		
		fields = new Object[]{likeParam.getIdUser(), likeParam.getIdComment()};
		labels = new String[]{Labels.ID_USER, Labels.ID_COMMENT};
		
		invalidFields = Util.validateParametersRequired(fields, labels);
		
		if(Util.isEmptyOrNull(invalidFields)) {
			CommentDAO commentDAO = new CommentDAO();
			likeParam = commentDAO.like(likeParam);
			
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
	public Comment getComment(@PathParam(Labels.ID) Long id) {
		
		Comment comment = new Comment();
		comment.setId(id);
		
		if(Util.isEmptyOrNullOrZero(id)) {
			comment.setStatusCode(Messages.PARAMETERS_REQUIRED.getStatusCode());
			comment.setStatusText(Messages.PARAMETERS_REQUIRED.getStatusText() +  ViewConstants.COLON_SPACE + Labels.ID);
			return comment;
		}
		
		CommentDAO dao = new CommentDAO();
		comment = dao.getComment(comment);

		return comment;
	}
	
	@GET
	@Path("/getListComment/{idPost}/{amount}/{firstResult}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ListComment getListComment(@PathParam("idPost") Long idPost, @PathParam(Labels.AMOUNT) Integer amount, @PathParam(Labels.FIRST_RESULT) Integer firstResult) {
		
		ListComment listComment = new ListComment();
		
		String invalidFields = null;
		Object[] fields = null;
		String[] labels = null;
		
			fields = new Object[]{idPost, amount};
			labels = new String[]{Labels.ID_POST, Labels.AMOUNT};
			
		invalidFields = Util.validateParametersRequired(fields, labels);
		
		if(Util.isEmptyOrNull(invalidFields)) {
			CommentDAO dao = new CommentDAO();
			listComment = dao.getListComment( idPost, amount, firstResult);
			
		} else {
			listComment.setStatusCode(Messages.PARAMETERS_REQUIRED.getStatusCode());
			listComment.setStatusText(Messages.PARAMETERS_REQUIRED.getStatusText() + ViewConstants.COLON_SPACE + invalidFields);
		}
		

		return listComment;
	}

}
