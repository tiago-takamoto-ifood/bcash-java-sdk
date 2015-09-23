package br.com.bcash.http;

import java.util.Map;

import org.apache.http.HttpEntity;

public class HttpRequest {

	private String url;

	private Map<String, String> headers;

	private HttpEntity body;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	public HttpEntity getBody() {
		return body;
	}

	public void setBody(HttpEntity body) {
		this.body = body;
	}

}
