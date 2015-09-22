package br.com.bcash.http.authentication;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.math.RandomUtils;

public class OAuth {

	private static final String OAUTH = "Oauth";

	private static final String REALM_PARAM = "realm";

	private static final String REALM = "https://api.bcash.com.br";

	private static final String CONSUMER_KEY_PARAM = "oauth_consumer_key";

	private static final String NONCE_PARAM = "oauth_nonce";

	private static final String SIGNATURE_METHOD_PARAM = "oauth_signature_method";

	private static final String SIGNATURE_METHOD = "PLAINTEXT";

	private static final String TIMESTAMP_PARAM = "oauth_timestamp";

	private static final String SIGNATURE_PARAM = "oauth_signature";

	private static final String VERSION_PARAM = "oauth_version";

	private static final String VERSION = "1.0";

	public Map<String, String> generateHeader(OAuthCredentials credentials) {
		String headerValue = generateHeaderValue(credentials);

		Map<String, String> header = new HashMap<String, String>();
		header.put(OAUTH, headerValue);

		return header;
	}

	private String generateHeaderValue(OAuthCredentials credentials) {
		String nonce = generateNonce();
		String timestamp = generateTimestamp();

		LinkedHashMap<String, String> parameters = generateParameters(credentials.getConsumerKey(), nonce, timestamp);
		String allParameters = concatParameters(parameters, "=", ",");

		StringBuilder builder = new StringBuilder();
		return builder.append(OAUTH).append(" ").append(allParameters).toString();
	}

	private LinkedHashMap<String, String> generateParameters(String consumerKey, String nonce, String timestamp) {
		LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
		parameters.put(REALM_PARAM, REALM);
		parameters.put(CONSUMER_KEY_PARAM, consumerKey);
		parameters.put(NONCE_PARAM, nonce);
		parameters.put(SIGNATURE_PARAM, generateSignature(consumerKey, nonce, timestamp));
		parameters.put(SIGNATURE_METHOD_PARAM, SIGNATURE_METHOD);
		parameters.put(TIMESTAMP_PARAM, timestamp);
		parameters.put(VERSION_PARAM, VERSION);
		return parameters;
	}

	private static LinkedHashMap<String, String> generateSignatureParameters(String consumerKey, String nonce, String timestamp) {
		LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
		parameters.put(CONSUMER_KEY_PARAM, consumerKey);
		parameters.put(NONCE_PARAM, nonce);
		parameters.put(SIGNATURE_METHOD_PARAM, SIGNATURE_METHOD);
		parameters.put(TIMESTAMP_PARAM, timestamp);
		parameters.put(VERSION_PARAM, VERSION);
		return parameters;
	}

	public static String generateSignature(final String consumerKey, final String nonce, final String timestamp) {
		LinkedHashMap<String, String> parameters = generateSignatureParameters(consumerKey, nonce, timestamp);

		String plainSignature = concatParameters(parameters, "=", "&");
		return new String(Base64.encodeBase64(plainSignature.getBytes()));
	}

	public static String generateNonce() {
		StringBuffer nonce = new StringBuffer();

		nonce.append(System.currentTimeMillis());
		nonce.append(RandomUtils.nextLong());

		return DigestUtils.md5Hex(nonce.toString());
	}

	public static String generateTimestamp() {
		return String.valueOf(new Date().getTime());
	}

	private static String concatParameters(Map<String, String> parameters, String separator, String delimiter) {
		StringBuilder builder = new StringBuilder();
		Iterator<Map.Entry<String, String>> iterator = parameters.entrySet().iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, String> parameter = iterator.next();
			builder.append(parameter.getKey()).append(separator).append(parameter.getValue());
			if (iterator.hasNext()) {
				builder.append(delimiter);
			}
		}

		return builder.toString();
	}

}
