package br.com.bcash.service;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.bcash.domain.transaction.TransactionStatusEnum;

public class Notification extends HttpServlet {

	private static final long serialVersionUID = -5338769121304568942L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		NotificationService notificationService = new NotificationService();
		String transactionId = notificationService.getTransactionId(req);
		// Ou String orderId = notificationService.getOrderId(req);

		/* consultar valor total do pedido no seu sistema */
		/* valor dos produtos + frete + acréscimo - disconto */
		BigDecimal transactionValue = new BigDecimal("201.00");

		try {
			if (notificationService.verify(req, resp, transactionValue)) {
				// dados válidos
				TransactionStatusEnum status = notificationService.getStatus(req);
				// alterar o status do seu pedido
				// ...
				// Notificar sucesso ao bcash
				notificationService.sendSuccess(resp);
			} else {
				// dados não procedem com informações existentes no Bcash, não alterar o status
			}
		} catch (ServiceException e) {
			// salvar em log os erros retornados em e.getErros();
			resp.sendError(HttpServletResponse.SC_BAD_GATEWAY, "Ocorreu um erro ao processar a requisição.");
		} catch (Exception e) {
			// salvar em log o erro de comunicação
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ocorreu um erro inesperado.");
		}
	}
}
