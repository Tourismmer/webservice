package com.tourismmer.app.json;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import com.tourismmer.app.model.Post;
import com.tourismmer.app.util.Util;

public class PostSerializer extends JsonSerializer<Post> {

	@Override
	public void serialize(Post o, JsonGenerator jgen, SerializerProvider sp)
			throws IOException, JsonProcessingException {
		
		jgen.writeStartObject();
		
		if(Util.isNotEmptyOrNullOrZero(o.getStatusCode())) jgen.writeObjectField("statusCode", o.getStatusCode());
		if(Util.isNotEmptyOrNullOrZero(o.getStatusText())) jgen.writeObjectField("statusText", o.getStatusText());
		
		jgen.writeObjectField("id", o.getId());
		if(Util.isNotEmptyOrNullOrZero(o.getDescription())) jgen.writeObjectField("description", o.getDescription());
		if(Util.isNotEmptyOrNullOrZero(o.getGroup()) && Util.isNotEmptyOrNullOrZero(o.getGroup().getId())) jgen.writeObjectField("group", o.getGroup());
		if(Util.isNotEmptyOrNullOrZero(o.getAuthor()) && Util.isNotEmptyOrNullOrZero(o.getAuthor().getId())) jgen.writeObjectField("author", o.getAuthor());
		if(Util.isNotEmptyOrNullOrZero(o.getImage()) && Util.isNotEmptyOrNullOrZero(o.getImage().getId())) jgen.writeObjectField("image", o.getImage());
		if(Util.isNotEmptyOrNullOrZero(o.getTypePost()) && Util.isNotEmptyOrNullOrZero(o.getTypePost().getId())) jgen.writeObjectField("typePost", o.getTypePost());
		if(Util.isNotEmptyOrNullOrZero(o.getUserList())) jgen.writeObjectField("userList", o.getUserList());
		if(Util.isNotEmptyOrNullOrZero(o.getDate())) jgen.writeObjectField("date", Util.dateToString(o.getDate().getTime(), Util.FORMAT_DATE_TIME_JSON));
		if(Util.isNotEmptyOrNullOrZero(o.getCountComment())) jgen.writeObjectField("countComment", o.getCountComment());
		if(Util.isNotEmptyOrNullOrZero(o.getCountUserGo())) jgen.writeObjectField("countUserGo", o.getCountUserGo());
		
		jgen.writeEndObject();
		
	}

}
