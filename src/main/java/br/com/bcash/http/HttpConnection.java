package br.com.bcash.http;

import java.io.IOException;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import br.com.bcash.util.PropertiesLoader;

public class HttpConnection {

	private static final String USER_AGENT;

	static {
		String userAgent = PropertiesLoader.getConfig("userAgent");
		USER_AGENT = userAgent != null && !userAgent.isEmpty() ? userAgent : "bcash-java-sdk/unknown-version";
	}

	public static HttpResponse get(HttpRequest request) throws ClientProtocolException, IOException {
		HttpGet httpGet = new HttpGet(request.getUrl());
		return send(httpGet, request);
	}

	public static HttpResponse post(HttpRequest request) throws ClientProtocolException, IOException {
		HttpPost httpPost = new HttpPost(request.getUrl());
		httpPost.setEntity(request.getBody());
		return send(httpPost, request);
	}

	public static HttpResponse send(HttpUriRequest httpMethod, HttpRequest request) throws ClientProtocolException, IOException {
		fillHeaders(httpMethod, request.getHeaders());
		CloseableHttpResponse httpResponse = getHttpClient().execute(httpMethod);
		return handleResponse(httpResponse);
	}

	private static CloseableHttpClient getHttpClient() {
		return HttpClients.createDefault();
	}

	private static HttpResponse handleResponse(CloseableHttpResponse httpResponse) throws IOException {
		HttpResponse response = new HttpResponse();
		response.setStatusCode(httpResponse.getStatusLine().getStatusCode());
		String body = "";
		try {
			HttpEntity entity = httpResponse.getEntity();
			if (entity != null) {
				body = EntityUtils.toString(entity);
				response.setBody(body);
			}

			EntityUtils.consume(entity);
		} finally {
			httpResponse.close();
		}

		return response;
	}

	private static void fillHeaders(HttpUriRequest httpRequest, Map<String, String> headers) {
		httpRequest.setHeader("User-Agent", USER_AGENT);

		if (headers == null) {
			return;
		}

		for (Map.Entry<String, String> header : headers.entrySet()) {
			httpRequest.addHeader(header.getKey(), header.getValue());
		}
	}

}
