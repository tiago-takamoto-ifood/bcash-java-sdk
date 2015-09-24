package br.com.bcash.http.authentication;

import br.com.bcash.util.PropertiesLoader;

public class BasicCredentials {

	private static final String EMAIL_PROPERTY = "bcash.credentials.email";

	private static final String TOKEN_PROPERTY = "bcash.credentials.token";

	private String email;

	private String token;

	public BasicCredentials(String email, String token) {
		this.email = email;
		this.token = token;
	}

	public String getEmail() {
		return email;
	}

	public String getToken() {
		return token;
	}

	public static BasicCredentials loadFromProperties() {
		String email = PropertiesLoader.get(EMAIL_PROPERTY);
		String token = PropertiesLoader.get(TOKEN_PROPERTY);
		if (email == null || email.isEmpty()) {
			throw new RuntimeException("Email das credenciais não foi definido.");
		}

		if (token == null || token.isEmpty()) {
			throw new RuntimeException("Token das credenciais não foi definido.");
		}

		return new BasicCredentials(email, token);
	}

}
