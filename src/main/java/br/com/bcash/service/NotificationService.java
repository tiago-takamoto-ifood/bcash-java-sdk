package br.com.bcash.service;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.bcash.config.Configuration;
import br.com.bcash.config.Environment;
import br.com.bcash.domain.transaction.TransactionStatusEnum;
import br.com.bcash.domain.transaction.search.TransactionSearchResponse;
import br.com.bcash.http.authentication.BasicCredentials;

public class NotificationService {

	private static final String TRANSACTION_ID_PARAM = "transacao_id";

	private static final String ORDER_ID_PARAM = "pedido";

	private static final String STATUS_ID_PARAM = "status_id";

	private static final String TEST_PARAM = "test";

	private static final String TEST = "test";

	private final BasicCredentials basicCredentials;

	/**
	 * Cria service de notificações para validar as notificações recebidas do Bcash carregando as credenciais fornecidas no properties.
	 * 
	 **/
	public NotificationService() {
		this.basicCredentials = BasicCredentials.loadFromProperties();
	}

	/**
	 * Cria service de notificações para validar as notificações recebidas do Bcash carregando as credenciais fornecidas.
	 * 
	 * @param basicCredentials
	 *            Credenciais que serão utilizadas para acessar a API REST do Bcash.
	 */
	public NotificationService(BasicCredentials basicCredentials) {
		this.basicCredentials = basicCredentials;
	}

	/**
	 * Confronta dados da notificação recebida e valor da transação com os dados existentes no Bcash. Este método retorna erro (HTTP 400)
	 * para o Bcash caso haja inconsistência nos dados. O status do seu pedido deve ser atualizado somente se for retornado {@code true}.
	 * 
	 * @param request
	 *            Manipulador da requisição enviada pelo Bcash para verificar os parâmetros enviados.
	 * @param response
	 *            Manipulador da resposta da requisição.
	 * @param transactionValue
	 *            Valor da transação enviada para o Bcash (Soma do valor dos produtos + frete + acrécimo - descontos).
	 * @return {@code true} caso os dados recebidos estejam iguais aos do Bcash; {@code false} caso contrário.
	 * @throws IOException
	 *             Caso ocorra um problema na comunicação com a API.
	 * @throws ServiceException
	 *             Caso o serviço retorne um erro.
	 */
	public boolean isValid(HttpServletRequest request, HttpServletResponse response, BigDecimal transactionValue) throws IOException,
			ServiceException {
		String transactionId = getTransactionId(request);
		String orderId = getOrderId(request);
		TransactionStatusEnum status = getStatus(request);

		if (!validateParameters(response, transactionId, orderId, status)) {
			return false;
		}

		if (isTest(request)) {
			return true;
		}

		TransactionSearchResponse transaction = new TransactionService(basicCredentials).searchById(transactionId);
		return compareStatus(status, transaction) && compareOrderId(orderId, transaction) && compareValue(transactionValue, transaction);
	}

	private boolean isTest(HttpServletRequest request) {
		String testParameter = request.getParameter(TEST_PARAM);
		return Environment.SANDBOX.equals(Configuration.getEnvironment()) && TEST.equals(testParameter);
	}

	public String getTransactionId(HttpServletRequest request) {
		return request.getParameter(TRANSACTION_ID_PARAM);
	}

	public String getOrderId(HttpServletRequest request) {
		return request.getParameter(ORDER_ID_PARAM);
	}

	public TransactionStatusEnum getStatus(HttpServletRequest request) {
		String statusId = request.getParameter(STATUS_ID_PARAM);
		if (statusId == null) {
			return TransactionStatusEnum.UNDEFINED;
		}

		return TransactionStatusEnum.from(Integer.valueOf(statusId));
	}

	private boolean validateParameters(HttpServletResponse response, String transactionId, String orderId, TransactionStatusEnum status)
			throws IOException {
		if (transactionId == null || transactionId.isEmpty()) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Identificador da transação inválido.");
			return false;
		}

		if (TransactionStatusEnum.UNDEFINED.equals(status)) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Status da transação inválido.");
			return false;
		}

		if (orderId == null || orderId.isEmpty()) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Identificador do pedido inválido.");
			return false;
		}

		return true;
	}

	private boolean compareStatus(TransactionStatusEnum status, TransactionSearchResponse transaction) {
		return transaction.getStatus().equals(status);
	}

	private boolean compareOrderId(String orderId, TransactionSearchResponse transaction) {
		return transaction.getOrderId().equals(orderId);
	}

	private boolean compareValue(BigDecimal transactionValue, TransactionSearchResponse transaction) {
		return transaction.getPaymentAmount().compareTo(transactionValue) == 0;
	}

	public void sendSuccess(HttpServletResponse resp) {
		resp.setStatus(HttpServletResponse.SC_OK);
	}

}
