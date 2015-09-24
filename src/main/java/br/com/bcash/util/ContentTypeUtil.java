package br.com.bcash.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpHeaders;
import org.apache.http.entity.ContentType;

import br.com.bcash.config.Configuration;

public class ContentTypeUtil {

	public static Map<String, String> generateAcceptHeader() {
		return generateAcceptHeader(Configuration.getDefaultContentType());
	}

	public static Map<String, String> generateAcceptHeader(String contentType) {
		Map<String, String> accept = new HashMap<String, String>();

		String acceptValue = generateAcceptValue(contentType);
		accept.put(HttpHeaders.ACCEPT, acceptValue);
		return accept;
	}

	private static String generateAcceptValue(String contentType) {
		return ContentType.create(contentType, Configuration.getEncode()).toString();
	}

}
