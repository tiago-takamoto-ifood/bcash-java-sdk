package br.com.bcash.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {

	private static final String PROPERTIES_FILE = "bcash.configuration";

	private static final String PROPERTIES_FILE_DEFAULT = "bcash.properties";

	private static final String PROPERTIES_CONFIG_FILE = "bcash-sdk.properties";

	private static final Properties PROPERTIES = loadResource(resolveResourceName());

	private static final Properties CONFIGS = loadResource(PROPERTIES_CONFIG_FILE);

	public static String get(String key) {
		return PROPERTIES.getProperty(key);
	}

	public static String getConfig(String key) {
		return CONFIGS.getProperty(key);
	}

	private static Properties loadResource(String resorceFileName) {
		final Properties properties = new Properties();

		try {
			final InputStream stream = PropertiesLoader.class.getClassLoader().getResourceAsStream(resorceFileName);
			properties.load(stream);
			stream.close();
		} catch (IOException e) {
			throw new RuntimeException("O arquivo de configuração do bcash não pode ser aberto: " + resorceFileName);
		}

		return properties;
	}

	private static String resolveResourceName() {
		String propertiesFile = System.getProperty(PROPERTIES_FILE);
		if (propertiesFile == null || propertiesFile.isEmpty()) {
			propertiesFile = PROPERTIES_FILE_DEFAULT;
		}

		return propertiesFile;
	}

}
