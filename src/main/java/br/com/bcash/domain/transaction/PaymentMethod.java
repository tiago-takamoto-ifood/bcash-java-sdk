package br.com.bcash.domain.transaction;

public class PaymentMethod {

	private Integer code;

	public PaymentMethod() {
	}

	public PaymentMethod(final Integer code) {
		this.code = code;
	}

	/**
	 * Meio de Pagamento utilizado para processar a transação.
	 * 
	 * @return code
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * Meio de Pagamento utilizado para processar a transação.<br>
	 * <b>Campo obrigatório</b><br>
	 * <br>
	 * *Vide enum: {@link PaymentMethodEnum}
	 * 
	 * @param code
	 *            ex.: PaymentMethodEnum.VISA
	 */
	public void setCode(final Integer code) {
		this.code = code;
	}
}
