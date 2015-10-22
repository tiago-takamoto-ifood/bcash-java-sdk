package br.com.bcash.domain.installment;

import java.util.List;

public class CalculateInstallmentsResponse {

	private List<PaymentType> paymentTypes;

	public final List<PaymentType> getPaymentTypes() {
		return paymentTypes;
	}

	public final void setPaymentTypes(List<PaymentType> paymentTypes) {
		this.paymentTypes = paymentTypes;
	}
}
