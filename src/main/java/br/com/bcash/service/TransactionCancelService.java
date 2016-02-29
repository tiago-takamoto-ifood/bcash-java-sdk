package br.com.bcash.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import br.com.bcash.config.Configuration;
import br.com.bcash.config.Environment;
import br.com.bcash.domain.error.ErrorList;
import br.com.bcash.domain.transaction.cancel.TransactionCancelResponse;
import br.com.bcash.http.HttpConnection;
import br.com.bcash.http.HttpRequest;
import br.com.bcash.http.HttpResponse;
import br.com.bcash.http.authentication.Basic;
import br.com.bcash.http.authentication.BasicCredentials;
import br.com.bcash.util.ContentTypeUtil;
import br.com.bcash.util.JsonUtil;
import br.com.bcash.util.XmlErrorUtil;

import com.google.gson.JsonSyntaxException;

class TransactionCancelService {

	private static final String CANCELLATION_SERVICE_URL = "transactions/%s/cancel";

	private BasicCredentials basicCredentials;

	private Environment environment;

	public TransactionCancelService(BasicCredentials basicCredentials) {
		this.basicCredentials = basicCredentials;
	}

	public TransactionCancelService environment(Environment environment) {
		this.environment = environment;
		return this;
	}

	public TransactionCancelResponse cancel(String transactionId) throws IOException, ServiceException {
		if (transactionId == null) {
			throw new IllegalArgumentException("Identificador da transação não pode ser null.");
		}

		HttpRequest httpRequest = generateCancellationRequest(transactionId);
		HttpResponse httpResponse = HttpConnection.post(httpRequest);

		if (!httpResponse.isSuccess()) {
			ErrorList errorList = adaptError(httpResponse.getBody());
			throw new ServiceException(errorList);
		}

		return JsonUtil.fromJson(httpResponse.getBody(), TransactionCancelResponse.class);
	}

	private ErrorList adaptError(String body) {

		try {
			return JsonUtil.fromJson(body, ErrorList.class);
		} catch (JsonSyntaxException e) {
			return XmlErrorUtil.fromXml(body);
		}
	}

	private HttpRequest generateCancellationRequest(String transactionId) {
		HttpRequest request = new HttpRequest();
		request.setUrl(Configuration.getApiUrl(environment) + String.format(CANCELLATION_SERVICE_URL, transactionId));

		Map<String, String> headers = new HashMap<String, String>();
		headers.putAll(Basic.generateHeader(basicCredentials));
		headers.putAll(ContentTypeUtil.generateAcceptHeader());
		request.setHeaders(headers);

		return request;
	}

}
