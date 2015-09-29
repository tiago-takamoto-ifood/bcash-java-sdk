package br.com.bcash.config;

public enum Environment {
	SANDBOX("sandbox"),
	PRODUCTION("prod");

	private String name;

	private Environment(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public static Environment fromName(String name) {
		for (Environment env : values()) {
			if (env.getName().equals(name)) {
				return env;
			}
		}

		return null;
	}

}
