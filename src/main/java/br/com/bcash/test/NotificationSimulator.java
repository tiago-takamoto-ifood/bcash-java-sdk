package br.com.bcash.test;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

import br.com.bcash.config.Configuration;
import br.com.bcash.domain.transaction.TransactionStatusEnum;

public class NotificationSimulator {

	private static final String TRANSACTION_ID_PARAM = "transacao_id";

	private static final String ORDER_ID_PARAM = "pedido";

	private static final String STATUS_ID_PARAM = "status_id";

	private static final String STATUS_PARAM = "status_id";

	private static final String TEST_PARAM = "test";

	private static final String TEST = "1";

	public String test(Notification notification) throws IOException {
		List<NameValuePair> valuePairs = generateParameters(notification);
		HttpResponse response = executePost(notification, valuePairs);

		int statusCode = response.getStatusLine().getStatusCode();
		String message = EntityUtils.toString(response.getEntity());

		StringBuilder builder = new StringBuilder();
		builder.append("HTTP ").append(statusCode);
		builder.append(" | ").append(message);

		return builder.toString();
	}

	private HttpResponse executePost(Notification notification,
			List<NameValuePair> valuePairs) throws IOException,
			ClientProtocolException {
		HttpEntity body = new UrlEncodedFormEntity(valuePairs,
				Configuration.getEncode());

		HttpClient client = getClient();
		HttpPost httpPost = new HttpPost(notification.getUrl());
		httpPost.setEntity(body);
		return client.execute(httpPost);
	}

	private CloseableHttpClient getClient() {
		SSLConnectionSocketFactory sslConnectionSocketFactory = getSelfSignedSSLFactory();
		return HttpClients.custom()
				.setSSLSocketFactory(sslConnectionSocketFactory).build();
	}

	private SSLConnectionSocketFactory getSelfSignedSSLFactory() {
		SSLContextBuilder builder = new SSLContextBuilder();
		SSLContext sslContext;
		try {
			builder.loadTrustMaterial(null, new AllowAllTrustStrategy());
			sslContext = builder.build();
		} catch (Exception e) {
			throw new RuntimeException(
					"Não foi possível criar o cliente para consumir o serviço.",
					e);
		}

		SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(
				sslContext, NoopHostnameVerifier.INSTANCE);
		return sslConnectionSocketFactory;
	}

	private List<NameValuePair> generateParameters(Notification notification) {
		List<NameValuePair> valuePairs = new ArrayList<NameValuePair>();
		valuePairs.add(new BasicNameValuePair(TRANSACTION_ID_PARAM,
				notification.getTransactionId()));
		valuePairs.add(new BasicNameValuePair(ORDER_ID_PARAM, notification
				.getOrderId()));
		valuePairs.add(new BasicNameValuePair(STATUS_ID_PARAM, String
				.valueOf(notification.getStatus().getCode())));
		valuePairs.add(new BasicNameValuePair(STATUS_PARAM, notification
				.getStatus().getDescription()));
		valuePairs.add(new BasicNameValuePair(STATUS_PARAM, notification
				.getStatus().getDescription()));
		valuePairs.add(new BasicNameValuePair(TEST_PARAM, TEST));
		return valuePairs;
	}

	public static class Notification {

		private String url;

		private String transactionId;

		private String orderId;

		private TransactionStatusEnum status;

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getTransactionId() {
			return transactionId;
		}

		public void setTransactionId(String transactionId) {
			this.transactionId = transactionId;
		}

		public String getOrderId() {
			return orderId;
		}

		public void setOrderId(String orderId) {
			this.orderId = orderId;
		}

		public TransactionStatusEnum getStatus() {
			return status;
		}

		public void setStatus(TransactionStatusEnum status) {
			this.status = status;
		}

	}

	class AllowAllTrustStrategy implements TrustStrategy {

		public boolean isTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
			return true;
		}

	}
}
