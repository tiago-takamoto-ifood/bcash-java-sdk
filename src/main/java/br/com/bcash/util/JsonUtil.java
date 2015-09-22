package br.com.bcash.util;

import com.google.gson.Gson;

public class JsonUtil {

	public static String toJson(Object object) {
		Gson gson = new Gson();
		return gson.toJson(object);
	}

	public static <T> T fromJson(String json, Class<T> clazz) {
		Gson gson = new Gson();
		return gson.fromJson(json, clazz);
	}

}
