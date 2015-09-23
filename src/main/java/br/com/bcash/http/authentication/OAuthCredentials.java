package br.com.bcash.http.authentication;

import br.com.bcash.util.PropertiesLoader;

public class OAuthCredentials {

	private static final String CONSUMER_KEY_PROPERTY = "bcash.credentials.consumerKey";

	private final String consumerKey;

	public OAuthCredentials(String consumerKey) {
		this.consumerKey = consumerKey;
	}

	public String getConsumerKey() {
		return consumerKey;
	}

	public static OAuthCredentials loadFromProperties() {
		String consumerKey = PropertiesLoader.get(CONSUMER_KEY_PROPERTY);
		if (consumerKey == null || consumerKey.isEmpty()) {
			throw new RuntimeException("Consumer key não foi definida no properties.");
		}

		return new OAuthCredentials(consumerKey);
	}
}
