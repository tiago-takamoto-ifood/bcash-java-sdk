package br.com.bcash.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicNameValuePair;

import br.com.bcash.config.Configuration;
import br.com.bcash.domain.error.ErrorList;
import br.com.bcash.domain.transaction.TransactionRequest;
import br.com.bcash.domain.transaction.TransactionResponse;
import br.com.bcash.http.HttpConnection;
import br.com.bcash.http.HttpRequest;
import br.com.bcash.http.HttpResponse;
import br.com.bcash.http.authentication.OAuth;
import br.com.bcash.http.authentication.OAuthCredentials;
import br.com.bcash.util.JsonUtil;

public class TransactionService {

	private static final String SERVICE_URL = "createTransaction/json/";

	private OAuthCredentials oAuthCredentials;

	/**
	 * Cria service de transação para realizar chamadas a API Rest de transações carregando as credenciais fornecidas no properties.
	 * 
	 **/
	public TransactionService() {
		this.oAuthCredentials = OAuthCredentials.loadFromProperties();
	}

	/**
	 * Cria service de transação para realizar chamadas a API Rest de transações utilizando as credenciais fornecidas.
	 * 
	 * @param oAuthCredentials
	 */
	public TransactionService(OAuthCredentials oAuthCredentials) {
		this.oAuthCredentials = oAuthCredentials;
	}

	public TransactionResponse create(TransactionRequest request) throws IOException, ServiceException {
		Map<String, String> authentication = OAuth.generateHeader(oAuthCredentials);
		HttpRequest httpRequest = new HttpRequest();
		httpRequest.setUrl(Configuration.getApiURL() + SERVICE_URL);
		httpRequest.setHeaders(authentication);

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("encode", "UTF_8"));
		nvps.add(new BasicNameValuePair("version", "1.0"));
		nvps.add(new BasicNameValuePair("data", JsonUtil.toJson(request)));

		UrlEncodedFormEntity body = new UrlEncodedFormEntity(nvps);
		body.setContentType(ContentType.APPLICATION_FORM_URLENCODED.getMimeType());
		httpRequest.setBody(body);

		HttpConnection httpConnection = new HttpConnection();
		HttpResponse httpResponse = httpConnection.post(httpRequest);

		if (httpResponse.getStatusCode() != HttpStatus.SC_OK) {
			throw new ServiceException(JsonUtil.fromJson(httpResponse.getBody(), ErrorList.class));
		}

		return JsonUtil.fromJson(httpResponse.getBody(), TransactionResponse.class);
	}

}
