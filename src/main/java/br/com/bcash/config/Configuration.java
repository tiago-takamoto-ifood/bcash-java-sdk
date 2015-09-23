package br.com.bcash.config;

import java.util.Arrays;
import java.util.List;

import br.com.bcash.util.PropertiesLoader;

public class Configuration {

	private static final List<String> environments = Arrays.asList("sandbox", "prod");

	private static final String DEFAULT_ENV = "prod";

	private static final String ENV_PROPERTY = "bcash.env";

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
			env = env.toLowerCase();
			if (environments.contains(env)) {
				return env;
			}
		}

		return DEFAULT_ENV;
	}
}
