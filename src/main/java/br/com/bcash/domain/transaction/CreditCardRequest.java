package br.com.bcash.domain.transaction;

public class CreditCardRequest {

	private String holder;

	private String number;

	private String securityCode;

	private Integer maturityMonth;

	private Integer maturityYear;

	/**
	 * Recupera o nome do titular do cartão de crédito.
	 * 
	 * @return holder
	 */
	public String getHolder() {
		return holder;
	}

	/**
	 * Nome do titular do cartão de crédito.<br>
	 * <i>Tamanho máximo: 100 caracteres</i>.<br>
	 * <b>Campo obrigatório</b>
	 * 
	 * @param holder
	 *            ex.: João D. F. Silva
	 */
	public void setHolder(final String holder) {
		this.holder = holder;
	}

	/**
	 * Recupera o número do cartão de crédito do cliente.
	 * 
	 * @return number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * Número do cartão de crédito do cliente.<br>
	 * <i>Tamanho máximo: 30 caracteres</i>.<br>
	 * <b>Campo obrigatório</b>
	 * 
	 * @param number
	 *            ex.: 4111111111111110
	 */
	public void setNumber(final String number) {
		this.number = number;
	}

	/**
	 * Recupera o código de segurança do cartão de crédito, geralmente encontra-se no verso do cartão.
	 * 
	 * @return securityCode
	 */
	public String getSecurityCode() {
		return securityCode;
	}

	/**
	 * Código de segurança do cartão de crédito, geralmente encontra-se no verso do cartão.<br>
	 * <b>Campo obrigatório</b>
	 * 
	 * @param securityCode
	 */
	public void setSecurityCode(final String securityCode) {
		this.securityCode = securityCode;
	}

	/**
	 * Recupera o mês de vencimento do cartão de crédito.
	 * 
	 * @return maturityMonth
	 */
	public Integer getMaturityMonth() {
		return maturityMonth;
	}

	/**
	 * Mês de vencimento do cartão de crédito.<br>
	 * <b>Campo obrigatório</b>
	 * 
	 * @param maturityMonth
	 */
	public void setMaturityMonth(final Integer maturityMonth) {
		this.maturityMonth = maturityMonth;
	}

	/**
	 * Recupera o ano de vencimento do cartão de crédito.<br>
	 * <b>Campo obrigatório.
	 * 
	 * @return maturityYear
	 */
	public Integer getMaturityYear() {
		return maturityYear;
	}

	/**
	 * Ano de vencimento do cartão de crédito.<br>
	 * <b>Campo obrigatório</b>
	 * 
	 * @param maturityYear
	 */
	public void setMaturityYear(final Integer maturityYear) {
		this.maturityYear = maturityYear;
	}
}
