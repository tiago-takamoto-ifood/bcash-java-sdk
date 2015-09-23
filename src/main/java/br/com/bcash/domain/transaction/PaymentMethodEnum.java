package br.com.bcash.domain.transaction;

public enum PaymentMethodEnum {
	BCASH_BALANCE(-1, PaymentMethodType.VIRTUAL_BALANCE),
	VISA(1, PaymentMethodType.CREDIT_CARD),
	MASTERCARD(2, PaymentMethodType.CREDIT_CARD),
	BANK_SLIP(10, PaymentMethodType.BANK_SLIP),
	AMERICAN_EXPRESS(37, PaymentMethodType.CREDIT_CARD),
	AURA(45, PaymentMethodType.CREDIT_CARD),
	DINERS(55, PaymentMethodType.CREDIT_CARD),
	HIPERCARD(56, PaymentMethodType.CREDIT_CARD),
	BB_ONLINE_TRANSFER(58, PaymentMethodType.ONLINE_TRANSFER),
	BRADESCO_ONLINE_TRANSFER(59, PaymentMethodType.ONLINE_TRANSFER),
	ITAU_ONLINE_TRANSFER(60, PaymentMethodType.ONLINE_TRANSFER),
	BANRISUL_ONLINE_TRANSFER(61, PaymentMethodType.ONLINE_TRANSFER),
	HSBC_ONLINE_TRANSFER(62, PaymentMethodType.ONLINE_TRANSFER),
	ELO(63, PaymentMethodType.CREDIT_CARD),
	CREDIT_CARD_TOKEN(99, PaymentMethodType.CREDIT_CARD);

	enum PaymentMethodType {
		VIRTUAL_BALANCE,
		CREDIT_CARD,
		BANK_SLIP,
		ONLINE_TRANSFER
	}

	private Integer code;

	private PaymentMethodType type;

	private PaymentMethodEnum(Integer code, PaymentMethodType type) {
		this.code = code;
		this.type = type;
	}

	public Integer getCode() {
		return code;
	}

	public boolean isCreditCard() {
		return PaymentMethodType.CREDIT_CARD.equals(type);
	}

	public boolean isBankSlip() {
		return PaymentMethodType.BANK_SLIP.equals(type);
	}

	public boolean isOnlineTransfer() {
		return PaymentMethodType.ONLINE_TRANSFER.equals(type);
	}

	public boolean isVirtualBalance() {
		return PaymentMethodType.VIRTUAL_BALANCE.equals(type);
	}

	public static PaymentMethodEnum fromCode(Integer code) throws IllegalArgumentException {
		for (PaymentMethodEnum paymentMethod : values()) {
			if (paymentMethod.getCode().equals(code)) {
				return paymentMethod;
			}
		}

		throw new IllegalArgumentException("Invalid payment method.");
	}

}
