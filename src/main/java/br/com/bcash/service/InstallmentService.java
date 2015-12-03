package br.com.bcash.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

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
import br.com.bcash.util.XmlErrorUtil;

import com.google.gson.JsonSyntaxException;

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
			ErrorList errorList = adaptError(httpResponse.getBody());
			throw new ServiceException(errorList);
		}

		return JsonUtil.fromJson(httpResponse.getBody(), CalculateInstallmentsResponse.class);
	}

	private ErrorList adaptError(String body) {

		try {
			return JsonUtil.fromJson(body, ErrorList.class);
		} catch (JsonSyntaxException e) {
			return XmlErrorUtil.fromXml(body);
		}
	}

	private HttpRequest generateCalculateRequest(CalculateInstallmentsRequest calculateRequest) {

		HttpRequest request = new HttpRequest();
		request.setUrl(Configuration.getApiUrl(environment) + "installments?" + generateCalculateParams(calculateRequest));

		Map<String, String> headers = new HashMap<String, String>();
		headers.putAll(Basic.generateHeader(basicCredentials));
		headers.putAll(ContentTypeUtil.generateAcceptHeader());
		request.setHeaders(headers);

		return request;
	}

	private String generateCalculateParams(CalculateInstallmentsRequest calculateRequest) {
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();

		if (calculateRequest.getAmount() != null) {
			parameters.add(new BasicNameValuePair("amount", calculateRequest.getAmount().toString()));
		}

		if (calculateRequest.getMaxInstallments() != null) {
			parameters.add(new BasicNameValuePair("maxInstallments", calculateRequest.getMaxInstallments().toString()));
		}
		if (calculateRequest.getIgnoreScheduledDiscount() != null) {
			parameters.add(new BasicNameValuePair("ignoreScheduledDiscount", calculateRequest.getIgnoreScheduledDiscount().toString()));
		}

		return URLEncodedUtils.format(parameters, Configuration.getEncode());
	}
}
