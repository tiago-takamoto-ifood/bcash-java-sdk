package br.com.bcash.domain.transaction;

public enum TransactionStatusEnum {
	AWAITING_PAYMENT(1, "Em andamento"),
	IN_REVIEW(2, "Em andamento"),
	APPROVED(3, "Aprovada"),
	COMPLETED(4, "Concluido"),
	IN_DISPUTE(5, "Em disputa"),
	REFUNDED(6, "Devolvida"),
	CANCELLED(7, "Cancelada"),
	CHARGEBACK(8, "Chargeback"),
	UNDEFINED(-1, "Status desconhecido");

	private int code;

	private String description;

	private TransactionStatusEnum(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public int getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public static TransactionStatusEnum from(int code) {
		for (TransactionStatusEnum status : values()) {
			if (status.getCode() == code) {
				return status;
			}
		}
		return UNDEFINED;
	}
}
