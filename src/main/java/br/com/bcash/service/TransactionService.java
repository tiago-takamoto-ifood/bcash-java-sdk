package br.com.bcash.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.DecoderException;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicNameValuePair;

import br.com.bcash.config.Configuration;
import br.com.bcash.domain.error.ErrorList;
import br.com.bcash.domain.transaction.TransactionRequest;
import br.com.bcash.domain.transaction.TransactionResponse;
import br.com.bcash.domain.transaction.cancel.TransactionCancelResponse;
import br.com.bcash.http.HttpConnection;
import br.com.bcash.http.HttpRequest;
import br.com.bcash.http.HttpResponse;
import br.com.bcash.http.authentication.Basic;
import br.com.bcash.http.authentication.BasicCredentials;
import br.com.bcash.http.authentication.OAuth;
import br.com.bcash.http.authentication.OAuthCredentials;
import br.com.bcash.util.ContentTypeUtil;
import br.com.bcash.util.EncoderUtil;
import br.com.bcash.util.JsonUtil;

public class TransactionService {

	private static final String CREATION_SERVICE_URL = "createTransaction/json/";

	private static final String CANCELLATION_SERVICE_URL = "transactions/%s/cancel";

	private final OAuthCredentials oAuthCredentials;

	private final BasicCredentials basicCredentials;

	/**
	 * Cria service de transação para realizar chamadas a API Rest de transações carregando as credenciais fornecidas no properties.
	 * 
	 **/
	public TransactionService() {
		this.oAuthCredentials = OAuthCredentials.loadFromProperties();
		this.basicCredentials = BasicCredentials.loadFromProperties();
	}

	/**
	 * Cria service de transação para realizar chamadas a API Rest de transações utilizando as credenciais fornecidas.
	 * 
	 * @param oAuthCredentials
	 */
	public TransactionService(OAuthCredentials oAuthCredentials) {
		this.oAuthCredentials = oAuthCredentials;
		this.basicCredentials = null;
	}

	public TransactionService(BasicCredentials basicCredentials) {
		this.oAuthCredentials = null;
		this.basicCredentials = basicCredentials;
	}

	public TransactionService(OAuthCredentials oAuthCredentials, BasicCredentials basicCredentials) {
		this.oAuthCredentials = oAuthCredentials;
		this.basicCredentials = basicCredentials;
	}

	/**
	 * Realiza a comunicação com o serviço de criação de transações com base nos dados informados.
	 * 
	 * @param request
	 *            Dados da transação
	 * @return
	 * @throws IOException
	 *             Caso ocorra um erro na comunicação.
	 * @throws ServiceException
	 *             Caso o serviço retorne um erro.
	 */
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

	private HttpRequest generateCreateTransactionRequest(TransactionRequest request) throws UnsupportedEncodingException {
		HttpRequest httpRequest = new HttpRequest();
		httpRequest.setUrl(Configuration.getApiURL() + CREATION_SERVICE_URL);

		Map<String, String> authentication = OAuth.generateHeader(oAuthCredentials);
		httpRequest.setHeaders(authentication);

		httpRequest.setBody(resolveCreateTransactionBody(request));

		return httpRequest;
	}

	private UrlEncodedFormEntity resolveCreateTransactionBody(TransactionRequest request) throws UnsupportedEncodingException {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("encode", "UTF_8"));
		nvps.add(new BasicNameValuePair("version", "1.0"));
		nvps.add(new BasicNameValuePair("data", JsonUtil.toJson(request)));

		UrlEncodedFormEntity body = new UrlEncodedFormEntity(nvps);
		body.setContentType(ContentType.APPLICATION_FORM_URLENCODED.getMimeType());

		return body;
	}

	/**
	 * Realiza o cancelamento da transação Bcash informada.
	 * 
	 * @param transactionId
	 *            Identificador da transação Bcash.
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 * @throws ServiceException
	 */
	public TransactionCancelResponse cancel(String transactionId) throws IOException, ServiceException {
		if (transactionId == null) {
			throw new IllegalArgumentException("Identificador da transação não pode ser null.");
		}

		HttpRequest httpRequest = generateCancellationRequest(transactionId);
		HttpResponse httpResponse = HttpConnection.post(httpRequest);

		if (!httpResponse.isSuccess()) {
			throw new ServiceException(JsonUtil.fromJson(httpResponse.getBody(), ErrorList.class));
		}

		return JsonUtil.fromJson(httpResponse.getBody(), TransactionCancelResponse.class);
	}

	private HttpRequest generateCancellationRequest(String transactionId) {
		HttpRequest request = new HttpRequest();
		request.setUrl(Configuration.getApiURL() + String.format(CANCELLATION_SERVICE_URL, transactionId));

		Map<String, String> headers = new HashMap<String, String>();
		headers.putAll(Basic.generateHeader(basicCredentials));
		headers.putAll(ContentTypeUtil.generateAcceptHeader());
		request.setHeaders(headers);

		return request;
	}
}
