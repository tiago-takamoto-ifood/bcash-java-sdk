package br.com.bcash.config;

import java.nio.charset.Charset;

import org.apache.http.Consts;
import org.apache.http.entity.ContentType;

import br.com.bcash.util.PropertiesLoader;

public class Configuration {

	private static final Environment DEFAULT_ENV = Environment.PRODUCTION;

	private static final String ENV_PROPERTY = "bcash.env";

	private static final String DEFAULT_CONTENT_TYPE = ContentType.APPLICATION_JSON.getMimeType();

	public static String getApiURL() {
		return getApiUrl(resolveEnvironment());
	}

	public static String getApiUrl(Environment environment) {
		return PropertiesLoader.getConfig("env." + environment.getName() + ".apiUrl");
	}

	public static String getURL() {
		return getURL(resolveEnvironment());
	}

	public static String getURL(Environment environment) {
		return PropertiesLoader.getConfig("env." + environment.getName() + ".url");
	}

	private static Environment resolveEnvironment() {
		String env = PropertiesLoader.get(ENV_PROPERTY);
		if (env != null && !env.isEmpty()) {
			Environment environment = Environment.fromName(env.toLowerCase());
			if (environment != null) {
				return environment;
			}
		}

		return DEFAULT_ENV;
	}

	public static Environment getEnvironment() {
		return resolveEnvironment();
	}

	public static Charset getEncode() {
		return Consts.ISO_8859_1;
	}

	public static String getDefaultContentType() {
		return DEFAULT_CONTENT_TYPE;
	}

}
