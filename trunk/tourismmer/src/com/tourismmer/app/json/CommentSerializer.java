package com.tourismmer.app.json;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import com.tourismmer.app.model.Comment;
import com.tourismmer.app.util.Util;

public class CommentSerializer extends JsonSerializer<Comment> {

	@Override
	public void serialize(Comment o, JsonGenerator jgen, SerializerProvider sp)
			throws IOException, JsonProcessingException {
		
		jgen.writeStartObject();
		
		if(Util.isNotEmptyOrNullOrZero(o.getStatusCode())) jgen.writeObjectField("statusCode", o.getStatusCode());
		if(Util.isNotEmptyOrNullOrZero(o.getStatusText())) jgen.writeObjectField("statusText", o.getStatusText());
		
		jgen.writeObjectField("id", o.getId());
		if(Util.isNotEmptyOrNullOrZero(o.getDescription())) jgen.writeObjectField("description", o.getDescription());
		if(Util.isNotEmptyOrNullOrZero(o.getPost()) && Util.isNotEmptyOrNullOrZero(o.getPost().getId())) jgen.writeObjectField("post", o.getPost());
		if(Util.isNotEmptyOrNullOrZero(o.getAuthor()) && Util.isNotEmptyOrNullOrZero(o.getAuthor().getId())) jgen.writeObjectField("author", o.getAuthor());
		
		jgen.writeEndObject();
		
	}

}
