package br.com.bcash.domain.transaction.cancel;

import br.com.bcash.domain.transaction.TransactionStatusEnum;

public class TransactionCancelResponse {

	private Long transactionId;

	private Integer transactionStatusId;

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public void setTransactionStatus(TransactionStatusEnum status) {
		this.transactionStatusId = status.getCode();
	}

	public TransactionStatusEnum getTransactionStatus() {
		return TransactionStatusEnum.from(transactionStatusId);
	}

}
