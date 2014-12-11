package com.tourismmer.app.json;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import com.tourismmer.app.model.Purpose;

public class PurposeSerializer extends JsonSerializer<Purpose> {

	@Override
	public void serialize(Purpose p, JsonGenerator jgen, SerializerProvider sp)
			throws IOException, JsonProcessingException {
		
		jgen.writeNumber(p.getId());
		
	}

}
