package br.com.bcash.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.DecoderException;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicNameValuePair;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.bcash.config.Configuration;
import br.com.bcash.domain.error.ErrorList;
import br.com.bcash.domain.error.ResponseError;
import br.com.bcash.domain.transaction.TransactionRequest;
import br.com.bcash.domain.transaction.TransactionResponse;
import br.com.bcash.domain.transaction.cancel.TransactionCancelResponse;
import br.com.bcash.domain.transaction.search.SearchError;
import br.com.bcash.domain.transaction.search.TransactionSearchErrorAdapter;
import br.com.bcash.domain.transaction.search.TransactionSearchResponse;
import br.com.bcash.domain.transaction.search.TransactionSearchResponseAdapter;
import br.com.bcash.domain.transaction.search.TransactionSearchResponseContainer;
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

	private static final String SEARCH_SERVICE_URL = "transacao/consulta";

	private static final String CREATE_SERVICE_DATA_PARAMETER = "data";

	private static final String CREATE_SERVICE_VERSION = "1.0";

	private static final String CREATE_SERVICE_VERSION_PARAMETER = "version";

	private static final String CREATE_SERVICE_ENCODE_PARAMETER = "encode";

	private static final String SEARCH_SERVICE_CONTENT_TYPE_PARAMETER = "tipo_retorno";

	private static final String SEARCH_SERVICE_ENCODE_PARAMETER = "codificacao";

	private static final String SEARCH_SERVICE_ORDER_ID_PARAMETER = "id_pedido";

	private static final String SEARCH_SERVICE_TRANSACTION_ID_PARAMETER = "id_transacao";

	private static final String SEARCH_SERVICE_JSON_CONTENT_TYPE = "2";

	private static final String SEARCH_SERVICE_UTF_8 = "1";

	private static final String SEARCH_SERVICE_ISO_8859_1 = "2";

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
		nvps.add(new BasicNameValuePair(CREATE_SERVICE_ENCODE_PARAMETER, Configuration.getEncode().displayName()));
		nvps.add(new BasicNameValuePair(CREATE_SERVICE_VERSION_PARAMETER, CREATE_SERVICE_VERSION));
		nvps.add(new BasicNameValuePair(CREATE_SERVICE_DATA_PARAMETER, JsonUtil.toJson(request)));

		UrlEncodedFormEntity body = new UrlEncodedFormEntity(nvps, Configuration.getEncode());
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

	/**
	 * Recupera os dados da transação por meio do identificador da transação Bcash informado.
	 * 
	 * @param transactionId
	 *            Identificador da transação Bcash
	 * @throws ServiceException
	 * @throws IOException
	 */
	public TransactionSearchResponse searchById(String transactionId) throws IOException, ServiceException {
		if (transactionId == null) {
			throw new IllegalArgumentException("Identificador da transação não pode ser null.");
		}

		return search(SEARCH_SERVICE_TRANSACTION_ID_PARAMETER, transactionId);
	}

	public TransactionSearchResponse searchByOrderId(String orderId) throws IOException, ServiceException {
		if (orderId == null) {
			throw new IllegalArgumentException("Identificador do pedido não pode ser null.");
		}

		return search(SEARCH_SERVICE_ORDER_ID_PARAMETER, orderId);
	}

	private String resolveSearchEncode() {
		if (Consts.ISO_8859_1.displayName().equals(Configuration.getEncode().displayName())) {
			return SEARCH_SERVICE_ISO_8859_1;
		}

		return SEARCH_SERVICE_UTF_8;
	}

	private TransactionSearchResponse search(String key, String value) throws IOException, ServiceException {
		HttpRequest httpRequest = gerenateSearchRequest(key, value);
		HttpResponse httpResponse = HttpConnection.post(httpRequest);

		if (!httpResponse.isSuccess()) {
			SearchError searchError = JsonUtil.fromJson(httpResponse.getBody(), SearchError.class);
			throw new ServiceException(TransactionSearchErrorAdapter.adapt(searchError));
		}
		
		TransactionSearchResponseContainer container = JsonUtil.fromJson(httpResponse.getBody(), TransactionSearchResponseContainer.class);
		return TransactionSearchResponseAdapter.adapt(container);
	}

	private HttpRequest gerenateSearchRequest(String key, String value) {
		HttpRequest request = new HttpRequest();
		request.setUrl(Configuration.getURL() + SEARCH_SERVICE_URL);

		request.setHeaders(Basic.generateHeader(basicCredentials));

		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(new BasicNameValuePair(SEARCH_SERVICE_ENCODE_PARAMETER, resolveSearchEncode()));
		parameters.add(new BasicNameValuePair(SEARCH_SERVICE_CONTENT_TYPE_PARAMETER, SEARCH_SERVICE_JSON_CONTENT_TYPE));
		parameters.add(new BasicNameValuePair(key, value));
		HttpEntity body = new UrlEncodedFormEntity(parameters, Configuration.getEncode());
		request.setBody(body);

		return request;
	}

}
