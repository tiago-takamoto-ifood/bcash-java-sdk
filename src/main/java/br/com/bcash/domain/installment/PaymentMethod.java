package br.com.bcash.domain.installment;

import java.math.BigDecimal;
import java.util.List;

public class PaymentMethod {

	private Integer id;

	private String name;

	private BigDecimal monthlyRate;

	private List<Installment> installments;

	public final Integer getId() {
		return id;
	}

	public final void setId(Integer id) {
		this.id = id;
	}

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final List<Installment> getInstallments() {
		return installments;
	}

	public final void setInstallments(List<Installment> calculateInstallments) {
		this.installments = calculateInstallments;
	}

	public final BigDecimal getMonthlyRate() {
		return monthlyRate;
	}

	public final void setMonthlyRate(BigDecimal monthlyRate) {
		this.monthlyRate = monthlyRate;
	}
}
