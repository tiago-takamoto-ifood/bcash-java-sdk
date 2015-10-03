package br.com.bcash.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.DecoderException;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicNameValuePair;

import br.com.bcash.config.Configuration;
import br.com.bcash.config.Environment;
import br.com.bcash.domain.error.ErrorList;
import br.com.bcash.domain.transaction.TransactionRequest;
import br.com.bcash.domain.transaction.TransactionResponse;
import br.com.bcash.http.HttpConnection;
import br.com.bcash.http.HttpRequest;
import br.com.bcash.http.HttpResponse;
import br.com.bcash.http.authentication.OAuth;
import br.com.bcash.http.authentication.OAuthCredentials;
import br.com.bcash.util.EncoderUtil;
import br.com.bcash.util.JsonUtil;

class TransactionCreateService {

	private static final String CREATION_SERVICE_URL = "createTransaction/json/";

	private static final String CREATE_SERVICE_DATA_PARAMETER = "data";

	private static final String CREATE_SERVICE_VERSION = "1.0";

	private static final String CREATE_SERVICE_VERSION_PARAMETER = "version";

	private static final String CREATE_SERVICE_ENCODE_PARAMETER = "encode";

	private OAuthCredentials oauthCredentials;
	
	private Environment environment;

	public TransactionCreateService(OAuthCredentials oauthCredentials) {
		this.oauthCredentials = oauthCredentials;
	}
	
	public TransactionCreateService environment(Environment environment) {
		this.environment = environment;
		return this;
	}

	public TransactionResponse create(TransactionRequest request) throws IOException, ServiceException {
		HttpRequest httpRequest = generateCreateTransactionRequest(request);
		HttpResponse httpResponse = HttpConnection.post(httpRequest);
		try {
			httpResponse.setBody(EncoderUtil.urlDecode(httpResponse.getBody()));
		} catch (DecoderException e) {
			throw new RuntimeException("Não foi possível decodar a resposta da API: " + httpResponse.getBody());
		}

		if (!httpResponse.isSuccess()) {
			throw new ServiceException(JsonUtil.fromJson(httpResponse.getBody(), ErrorList.class));
		}

		return JsonUtil.fromJson(httpResponse.getBody(), TransactionResponse.class);
	}

	private HttpRequest generateCreateTransactionRequest(TransactionRequest request)
			throws UnsupportedEncodingException {
		HttpRequest httpRequest = new HttpRequest();
		httpRequest.setUrl(Configuration.getApiUrl(environment) + CREATION_SERVICE_URL);

		Map<String, String> authentication = OAuth.generateHeader(oauthCredentials);
		httpRequest.setHeaders(authentication);

		httpRequest.setBody(resolveCreateTransactionBody(request));

		return httpRequest;
	}

	private UrlEncodedFormEntity resolveCreateTransactionBody(TransactionRequest request) throws UnsupportedEncodingException {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair(CREATE_SERVICE_ENCODE_PARAMETER, Configuration.getEncode().displayName()));
		nvps.add(new BasicNameValuePair(CREATE_SERVICE_VERSION_PARAMETER, CREATE_SERVICE_VERSION));
		nvps.add(new BasicNameValuePair(CREATE_SERVICE_DATA_PARAMETER, JsonUtil.toJson(request)));

		UrlEncodedFormEntity body = new UrlEncodedFormEntity(nvps, Configuration.getEncode());
		body.setContentType(ContentType.APPLICATION_FORM_URLENCODED.getMimeType());

		return body;
	}

}
