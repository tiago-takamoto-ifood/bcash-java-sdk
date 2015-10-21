package br.com.bcash.domain.installment;

import java.util.ArrayList;
import java.util.List;

public class PaymentType {

	private String name;

	private List<PaymentMethod> paymentMethods;

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final List<PaymentMethod> getPaymentMethods() {
		if (paymentMethods == null) {
			paymentMethods = new ArrayList<PaymentMethod>();
		}
		return paymentMethods;
	}

	public final void setPaymentMethods(List<PaymentMethod> paymentMethods) {
		this.paymentMethods = paymentMethods;
	}
}
