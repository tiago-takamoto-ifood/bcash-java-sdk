package br.com.bcash.http;

import java.io.IOException;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpConnection {

	public HttpResponse get(HttpRequest request) throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(request.getUrl());
		fillHeaders(httpGet, request.getHeaders());

		CloseableHttpResponse httpResponse = httpclient.execute(httpGet);
		return handleResponse(httpResponse);
	}

	private HttpResponse handleResponse(CloseableHttpResponse httpResponse) throws IOException {
		HttpResponse response = new HttpResponse();
		response.setStatusCode(httpResponse.getStatusLine().getStatusCode());
		try {
			System.out.println(httpResponse.getStatusLine());
			HttpEntity entity = httpResponse.getEntity();
			if (entity != null) {
				// long len = entity.getContentLength();
				// if (len > 0 && len < 2048) {
				response.setBody(EntityUtils.toString(entity));
				// }
			}

			EntityUtils.consume(entity);
		} finally {
			httpResponse.close();
		}
		return response;
	}

	private void fillHeaders(HttpRequestBase httpRequest, Map<String, String> headers) {
		if (headers == null) {
			return;
		}

		for (Map.Entry<String, String> header : headers.entrySet()) {
			httpRequest.addHeader(header.getKey(), header.getValue());
		}
	}

	public HttpResponse post(HttpRequest request) throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(request.getUrl());
		fillHeaders(httpPost, request.getHeaders());
		httpPost.setEntity(request.getBody());

		CloseableHttpResponse httpResponse = httpclient.execute(httpPost);
		return handleResponse(httpResponse);
	}
}
