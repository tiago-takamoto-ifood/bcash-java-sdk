package br.com.bcash.domain.transaction;

import org.apache.commons.lang.builder.ToStringBuilder;

public class TransactionResponse {

	private String transactionId;

	private String orderId;

	private Integer status;

	private String descriptionStatus;

	private String paymentLink;

	private String message;

	private Integer cancellationCode;
	
	private String permanentToken;

	/**
	 * Recupera o identificador da transação no Bcash.
	 * 
	 * @return transactionId
	 */
	public String getTransactionId() {
		return transactionId;
	}

	/**
	 * Identificador da transação no Bcash.
	 * 
	 * @param transactionId
	 */
	public void setTransactionId(final String transactionId) {
		this.transactionId = transactionId;
	}

	/**
	 * Recupera o identificador da transação enviado pela loja.
	 * 
	 * @return orderId
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * Identificador da transação enviado pela loja.
	 * 
	 * @param orderId
	 */
	public void setOrderId(final String orderId) {
		this.orderId = orderId;
	}

	/**
	 * Recupera o código do status da transação.
	 * 
	 * @return status
	 */
	public TransactionStatusEnum getStatus() {
		return TransactionStatusEnum.from(status);
	}

	/**
	 * Código do status da transação.
	 * 
	 * @param status
	 */
	public void setStatus(final TransactionStatusEnum status) {
		this.status = status == null ? null : status.getCode();
	}

	/**
	 * Recupera a descrição do status da transação.
	 * 
	 * @return descriptionStatus
	 */
	public String getDescriptionStatus() {
		return descriptionStatus;
	}

	/**
	 * Descrição do status da transação.
	 * 
	 * @param descriptionStatus
	 */
	public void setDescriptionStatus(final String descriptionStatus) {
		this.descriptionStatus = descriptionStatus;
	}

	/**
	 * Recupera o link para pagamento para transações com os meios de boleto e transferência online.
	 * 
	 * @return paymentLink
	 */
	public String getPaymentLink() {
		return paymentLink;
	}

	/**
	 * Link para pagamento para transações com os meios de boleto e transferência online.
	 * 
	 * @param paymentLink
	 */
	public void setPaymentLink(final String paymentLink) {
		this.paymentLink = paymentLink;
	}

	/**
	 * Recupera a mensagem de erro amigável do acquirer
	 * 
	 * @return message;
	 */
	public String getMessage() {
		return this.message;
	}

	/**
	 * Atribui a mensagem de erro amigável vinda do acquirer de cartão de crédito.
	 * 
	 * @param message
	 */
	public void setMessage(final String message) {
		this.message = message;
	}

	/**
	 * 
	 * Recupera o códido de erro Bcash em caso de cancelamento
	 * 
	 * @return the cancellationCode
	 */
	public Integer getCancellationCode() {
		return cancellationCode;
	}
	
	/**
	 * Recupera token que representa o cartão de crédito salvo para compras futuras. Pode ser utilizado em soluções OneClickBuy. 
	 * @return token do cartão de crédito
	 */
	public String getPermanentToken() {
		return permanentToken;
	}
	
	/**
	 * AStribui token que representa o cartão de crédito salvo para compras futuras.
	 * @param permanentToken token do cartão de crédito
	 */
	public void setPermanentToken(String permanentToken) {
		this.permanentToken = permanentToken;
	}

	/**
	 * 
	 * Atribui o códido de erro Bcash em caso de cancelamento
	 * 
	 * @param cancellationCode
	 *            the cancellationCode to set
	 */
	public void setCancellationCode(final Integer cancellationCode) {
		this.cancellationCode = cancellationCode;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("transaction", this.getTransactionId()).toString();
	}

}
