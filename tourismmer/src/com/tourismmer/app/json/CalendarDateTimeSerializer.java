package com.tourismmer.app.json;

import java.io.IOException;
import java.util.Calendar;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import com.tourismmer.app.util.Util;

public class CalendarDateTimeSerializer extends JsonSerializer<Calendar> {

	@Override
	public void serialize(Calendar c, JsonGenerator jgen, SerializerProvider sp)
			throws IOException, JsonProcessingException {
		
		jgen.writeString(Util.dateToString(c.getTime(), Util.FORMAT_DATE_TIME_JSON));
		
	}

}
