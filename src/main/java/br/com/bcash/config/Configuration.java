package br.com.bcash.config;

import java.nio.charset.Charset;

import org.apache.http.Consts;
import org.apache.http.entity.ContentType;

import br.com.bcash.util.PropertiesLoader;

public class Configuration {

	private static final String DEFAULT_ENV = "prod";

	private static final String ENV_PROPERTY = "bcash.env";

	private static final String DEFAULT_CONTENT_TYPE = ContentType.APPLICATION_JSON.getMimeType();

	public static String getApiURL() {
		String env = resolveEnvironment();
		return PropertiesLoader.getConfig("env." + env + ".apiUrl");
	}

	public static String getURL() {
		String env = resolveEnvironment();
		return PropertiesLoader.getConfig("env." + env + ".url");
	}

	private static String resolveEnvironment() {
		String env = PropertiesLoader.get(ENV_PROPERTY);
		if (env != null && !env.isEmpty()) {
			Environment environment = Environment.fromName(env.toLowerCase());
			if (environment != null) {
				return env;
			}
		}

		return DEFAULT_ENV;
	}

	public static Environment getEnvironment() {
		return Environment.fromName(resolveEnvironment());
	}

	public static Charset getEncode() {
		return Consts.UTF_8;
	}

	public static String getDefaultContentType() {
		return DEFAULT_CONTENT_TYPE;
	}
}
