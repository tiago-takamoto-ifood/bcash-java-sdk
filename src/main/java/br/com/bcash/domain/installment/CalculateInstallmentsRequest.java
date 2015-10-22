package br.com.bcash.domain.installment;

import java.math.BigDecimal;

public class CalculateInstallmentsRequest {

	private BigDecimal amount;

	private Integer maxInstallments;

	private Boolean ignoreScheduledDiscount;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Integer getMaxInstallments() {
		return maxInstallments;
	}

	public void setMaxInstallments(Integer maxInstallments) {
		this.maxInstallments = maxInstallments;
	}

	public Boolean getIgnoreScheduledDiscount() {
		return ignoreScheduledDiscount;
	}

	public void setIgnoreScheduledDiscount(Boolean ignoreScheduledDiscount) {
		this.ignoreScheduledDiscount = ignoreScheduledDiscount;
	}
}
