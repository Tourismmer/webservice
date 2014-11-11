package com.tourismmer.app.json;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import com.tourismmer.app.model.User;
import com.tourismmer.app.util.Util;

public class UserSerializer extends JsonSerializer<User> {

	@Override
	public void serialize(User u, JsonGenerator jgen, SerializerProvider sp)
			throws IOException, JsonProcessingException {
		
		jgen.writeStartObject();
		jgen.writeNumberField("id", u.getId());
		if(Util.isNotEmptyOrNullOrZero(u.getName())) jgen.writeStringField("name", u.getName());
		jgen.writeEndObject();
		
	}

}
