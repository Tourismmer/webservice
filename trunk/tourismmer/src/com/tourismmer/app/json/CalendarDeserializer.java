package com.tourismmer.app.json;

import java.io.IOException;
import java.util.Calendar;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.ObjectCodec;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

import com.tourismmer.app.util.Util;

public class CalendarDeserializer extends JsonDeserializer<Calendar> {

	@Override
	public Calendar deserialize(JsonParser jsonParser, DeserializationContext arg1)
			throws IOException, JsonProcessingException {
		
		ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(Util.stringToDate(node.get("birthday").getTextValue(), Util.FORMATO_DATA_JSON));
        
		return cal;
		
	}


}
