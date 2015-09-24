package br.com.bcash.http;

import org.apache.http.HttpStatus;

public class HttpResponse {

	private Integer statusCode;

	private String body;

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public boolean isSuccess() {
		return statusCode != null && statusCode == HttpStatus.SC_OK;
	}
}
