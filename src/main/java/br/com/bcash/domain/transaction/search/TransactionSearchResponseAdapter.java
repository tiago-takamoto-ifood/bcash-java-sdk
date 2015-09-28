package br.com.bcash.domain.transaction.search;

import java.math.BigDecimal;
import java.math.RoundingMode;

import br.com.bcash.domain.customer.Address;
import br.com.bcash.domain.customer.Customer;
import br.com.bcash.domain.customer.StateEnum;
import br.com.bcash.domain.transaction.PaymentMethodEnum;
import br.com.bcash.domain.transaction.TransactionStatusEnum;
import br.com.bcash.domain.transaction.search.TransactionSearchResponseContainer.Product;
import br.com.bcash.domain.transaction.search.TransactionSearchResponseContainer.Transaction;

public class TransactionSearchResponseAdapter {

	public static TransactionSearchResponse adapt(TransactionSearchResponseContainer container) {
		if (container == null || container.getTransaction() == null) {
			return null;
		}
		
		TransactionSearchResponse response = new TransactionSearchResponse();
		
		Transaction transaction = container.getTransaction();
		response.setTransactionId(transaction.getTransactionId());
		response.setOrderId(transaction.getOrderId());
		response.setSellerEmail(transaction.getSellerEmail());
		response.setCreatedDate(transaction.getCreatedDate());
		response.setCreditDate(transaction.getCreditDate());
		response.setPaymentAmount(transaction.getPaymentAmount());
		response.setPaymentAmountWithRate(transaction.getPaymentAmountWithRate());
		response.setFavoredPaymentAmount(transaction.getFavoredPaymentAmount());
		response.setDiscount(transaction.getDiscount());
		response.setAddition(transaction.getAddition());
		response.setPaymentMethod(PaymentMethodEnum.fromCode(transaction.getPaymentMethodId()));
		response.setInstallments(transaction.getInstallments());
		response.setStatus(TransactionStatusEnum.from(transaction.getStatusId()));
		response.setLastStatusUpdate(transaction.getLastStatusUpdate());
		response.setShippingCost(transaction.getShippingCost());
		response.setShippingType(transaction.getShippingType());
		response.setFree(transaction.getFree());
		
		Customer customer = new Customer();
		customer.setMail(transaction.getEmail());
		customer.setCompanyName(transaction.getCompanyName());
		customer.setName(transaction.getName());
		customer.setCnpj(transaction.getCnpj());
		customer.setCpf(transaction.getCpf());
		customer.setPhone(transaction.getPhone());
		
		Address address = new Address();
		address.setAddress(transaction.getAddress());
		address.setCity(transaction.getCity());
		address.setComplement(transaction.getComplement());
		address.setNeighborhood(transaction.getNeighborhood());
		address.setState(StateEnum.fromAbbreviation(transaction.getState()));
		address.setZipCode(transaction.getZipCode());
		customer.setAddress(address);
		response.setBuyer(customer);
		
		for (Product product : transaction.getProducts()) {
			br.com.bcash.domain.transaction.Product p = new br.com.bcash.domain.transaction.Product();
			p.setCode(product.getCode());
			p.setDescription(product.getDescription());
			p.setAmount(product.getAmount());
			p.setValue(product.getValue().divide(new BigDecimal(product.getAmount())).setScale(2, RoundingMode.CEILING));
			p.setExtraDescription(product.getExtraDescription());
		}
		
		return response;
	}
	
}
