package br.com.bcash.domain.installment;

import java.math.BigDecimal;

public class Installment {

	private Integer number;

	private BigDecimal amount;

	private BigDecimal installmentAmount;

	private BigDecimal rate;

	public final Integer getNumber() {
		return number;
	}

	public final void setNumber(Integer number) {
		this.number = number;
	}

	public final BigDecimal getAmount() {
		return amount;
	}

	public final void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getInstallmentAmount() {
		return installmentAmount;
	}

	public void setInstallmentAmount(BigDecimal installmentAmount) {
		this.installmentAmount = installmentAmount;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
}
