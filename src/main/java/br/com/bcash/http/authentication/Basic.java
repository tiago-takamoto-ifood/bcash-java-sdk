package br.com.bcash.http.authentication;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpHeaders;

import br.com.bcash.util.EncoderUtil;

public class Basic {

	private static final String BASIC = "Basic";

	public static Map<String, String> generateHeader(BasicCredentials credentials) {
		if (credentials == null) {
			throw new IllegalArgumentException("Email e token não foram configurados para autenticação basic.");
		}

		String headerValue = generateHeaderValue(credentials);

		Map<String, String> header = new HashMap<String, String>();
		header.put(HttpHeaders.AUTHORIZATION, headerValue);

		return header;
	}

	private static String generateHeaderValue(BasicCredentials credentials) {
		String value = generateBasicAuthToken(credentials);

		StringBuilder builder = new StringBuilder();
		builder.append(BASIC).append(" ").append(value);
		return builder.toString();
	}

	private static String generateBasicAuthToken(BasicCredentials credentials) {
		StringBuilder builder = new StringBuilder();
		builder.append(credentials.getEmail()).append(":").append(credentials.getToken());
		String value = EncoderUtil.base64Encode(builder.toString());
		return value;
	}

}
