package br.com.bcash.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import br.com.bcash.config.Configuration;
import br.com.bcash.config.Environment;
import br.com.bcash.domain.error.ErrorList;
import br.com.bcash.domain.installment.CalculateInstallmentsRequest;
import br.com.bcash.domain.installment.CalculateInstallmentsResponse;
import br.com.bcash.http.HttpConnection;
import br.com.bcash.http.HttpRequest;
import br.com.bcash.http.HttpResponse;
import br.com.bcash.http.authentication.Basic;
import br.com.bcash.http.authentication.BasicCredentials;
import br.com.bcash.util.ContentTypeUtil;
import br.com.bcash.util.JsonUtil;

public class InstallmentService {

	private final BasicCredentials basicCredentials;

	private final Environment environment;

	public InstallmentService() {
		this.basicCredentials = BasicCredentials.loadFromProperties();
		this.environment = Configuration.getEnvironment();
	}

	public InstallmentService(BasicCredentials basicCredentials) {
		this.basicCredentials = basicCredentials;
		this.environment = Configuration.getEnvironment();
	}

	public InstallmentService(BasicCredentials basicCredentials, Environment environment) {
		this.basicCredentials = basicCredentials;
		this.environment = environment;
	}

	public InstallmentService(Environment environment) {
		this.basicCredentials = BasicCredentials.loadFromProperties();
		this.environment = environment;
	}

	public CalculateInstallmentsResponse calculate(CalculateInstallmentsRequest calculateRequest) throws IOException, ServiceException {

		HttpRequest httpRequest = generateCalculateRequest(calculateRequest);
		HttpResponse httpResponse = HttpConnection.get(httpRequest);

		if (!httpResponse.isSuccess()) {
			throw new ServiceException(JsonUtil.fromJson(httpResponse.getBody(), ErrorList.class));
		}

		return JsonUtil.fromJson(httpResponse.getBody(), CalculateInstallmentsResponse.class);
	}

	private HttpRequest generateCalculateRequest(CalculateInstallmentsRequest calculateRequest) {

		HttpRequest request = new HttpRequest();
		request.setUrl(Configuration.getApiUrl(environment) + "/installments" + generateCalculateParams(calculateRequest));

		Map<String, String> headers = new HashMap<String, String>();
		headers.putAll(Basic.generateHeader(basicCredentials));
		headers.putAll(ContentTypeUtil.generateAcceptHeader());
		request.setHeaders(headers);

		return request;
	}

	private String generateCalculateParams(CalculateInstallmentsRequest calculateRequest) {

		String params = "?";

		if (calculateRequest.getAmount() != null) {
			params += "amount=" + calculateRequest.getAmount() + "&";
		}
		if (calculateRequest.getMaxInstallments() != null) {
			params += "maxInstallments=" + calculateRequest.getMaxInstallments() + "&";
		}
		if (calculateRequest.getIgnoreScheduledDiscount() != null) {
			params += "ignoreScheduledDiscount=" + calculateRequest.getIgnoreScheduledDiscount();
		}

		return params;
	}
}
