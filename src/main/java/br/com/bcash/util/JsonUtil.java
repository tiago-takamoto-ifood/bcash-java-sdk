package br.com.bcash.util;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class JsonUtil {

	public static String toJson(Object object) {
		Gson gson = createParser();
		return gson.toJson(object);
	}

	public static <T> T fromJson(String json, Class<T> clazz) {
		Gson gson = createParser();
		return gson.fromJson(json, clazz);
	}

	private static Gson createParser() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Date.class, new BrDateSerializer());
		
		return gsonBuilder.create();
	}
}

class BrDateSerializer implements JsonDeserializer<Date> {
	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

	public Date deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		
		if (json.getAsString() == null || json.getAsString().isEmpty()) {
			return null;
		}
		
		try {
            return df.parse(json.getAsString());
        } catch (ParseException e) {
            throw new JsonParseException(e);
        }
	}
}
