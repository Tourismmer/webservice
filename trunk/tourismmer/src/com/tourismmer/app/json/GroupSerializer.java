package com.tourismmer.app.json;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import com.tourismmer.app.model.Group;
import com.tourismmer.app.util.Util;

public class GroupSerializer  extends JsonSerializer<Group> {

	@Override
	public void serialize(Group o, JsonGenerator jgen, SerializerProvider sp)
			throws IOException, JsonProcessingException {
		
		jgen.writeStartObject();
		
		if(Util.isNotEmptyOrNullOrZero(o.getStatusCode())) jgen.writeObjectField("statusCode", o.getStatusCode());
		if(Util.isNotEmptyOrNullOrZero(o.getStatusText())) jgen.writeObjectField("statusText", o.getStatusText());
		
		jgen.writeObjectField("id", o.getId());
		if(Util.isNotEmptyOrNullOrZero(o.getDestination())) jgen.writeObjectField("destination", o.getDestination());
		if(Util.isNotEmptyOrNullOrZero(o.getPurpose()) && Util.isNotEmptyOrNullOrZero(o.getPurpose().getId())) jgen.writeObjectField("purpose", o.getPurpose());
		if(Util.isNotEmptyOrNullOrZero(o.getOwner()) && Util.isNotEmptyOrNullOrZero(o.getOwner().getId())) jgen.writeObjectField("owner", o.getOwner());
		if(Util.isNotEmptyOrNullOrZero(o.getUserList())) jgen.writeObjectField("userList", o.getUserList());
		if(Util.isNotEmptyOrNullOrZero(o.getImage()) && Util.isNotEmptyOrNullOrZero(o.getImage().getId())) jgen.writeObjectField("image", o.getImage());
		if(Util.isNotEmptyOrNullOrZero(o.getDate())) jgen.writeObjectField("date", o.getDate());
		if(Util.isNotEmptyOrNullOrZero(o.getCountUser())) jgen.writeObjectField("countUser", o.getCountUser());
		
		jgen.writeEndObject();
		
		
	}

}
