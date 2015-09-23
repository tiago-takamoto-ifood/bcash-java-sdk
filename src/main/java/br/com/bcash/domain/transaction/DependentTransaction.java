package br.com.bcash.domain.transaction;

import java.math.BigDecimal;

public class DependentTransaction {

	private String email;

	private BigDecimal value;

	public DependentTransaction() {
	}

	public DependentTransaction(final String email, final BigDecimal value) {
		this.email = email;
		this.value = value;
	}

	/**
	 * Recupera o email da conta dependente.
	 * 
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Email da conta dependente.<br>
	 * <i>Tamanho máximo: 80 caracteres</i>.<br>
	 * 
	 * @param email
	 *            ex.: empresa@provedor.com
	 */
	public void setEmail(final String email) {
		this.email = email;
	}

	/**
	 * Recupera o valor monetário da transação dependente.
	 * 
	 * @return value
	 */
	public BigDecimal getValue() {
		return value;
	}

	/**
	 * Valor monetário da transação dependente.
	 * 
	 * @param value
	 *            ex.: new BigDecimal("10.95")
	 */
	public void setValue(final BigDecimal value) {
		this.value = value;
	}

}
