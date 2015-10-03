package br.com.bcash.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

import br.com.bcash.config.Configuration;
import br.com.bcash.config.Environment;
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
import br.com.bcash.util.JsonUtil;

class TransactionSearchService {

	private static final String SEARCH_SERVICE_URL = "transacao/consulta";

	private static final String SEARCH_SERVICE_CONTENT_TYPE_PARAMETER = "tipo_retorno";

	private static final String SEARCH_SERVICE_ENCODE_PARAMETER = "codificacao";

	private static final String SEARCH_SERVICE_ORDER_ID_PARAMETER = "id_pedido";

	private static final String SEARCH_SERVICE_TRANSACTION_ID_PARAMETER = "id_transacao";

	private static final String SEARCH_SERVICE_JSON_CONTENT_TYPE = "2";

	private static final String SEARCH_SERVICE_UTF_8 = "1";

	private static final String SEARCH_SERVICE_ISO_8859_1 = "2";

	private final BasicCredentials basicCredentials;

	private Environment environment;

	public TransactionSearchService(BasicCredentials basicCredentials) {
		this.basicCredentials = basicCredentials;
	}
	
	public TransactionSearchService environment(Environment environment) {
		this.environment = environment;
		return this;
	}

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
		HttpRequest httpRequest = generateSearchRequest(key, value);
		HttpResponse httpResponse = HttpConnection.post(httpRequest);

		if (!httpResponse.isSuccess()) {
			SearchError searchError = JsonUtil.fromJson(httpResponse.getBody(), SearchError.class);
			throw new ServiceException(TransactionSearchErrorAdapter.adapt(searchError));
		}

		TransactionSearchResponseContainer container = JsonUtil.fromJson(httpResponse.getBody(), TransactionSearchResponseContainer.class);
		return TransactionSearchResponseAdapter.adapt(container);
	}

	private HttpRequest generateSearchRequest(String key, String value) {
		HttpRequest request = new HttpRequest();
		request.setUrl(Configuration.getURL(environment) + SEARCH_SERVICE_URL);

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
