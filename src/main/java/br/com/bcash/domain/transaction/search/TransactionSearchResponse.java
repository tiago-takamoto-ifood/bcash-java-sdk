package br.com.bcash.domain.transaction.search;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import br.com.bcash.domain.customer.Customer;
import br.com.bcash.domain.transaction.PaymentMethodEnum;
import br.com.bcash.domain.transaction.TransactionStatusEnum;

import com.google.gson.annotations.SerializedName;

public class TransactionSearchResponse {
	
	private String transactionId;

	private String orderId;

	private String sellerEmail;

	private Date createdDate;

	private Date creditDate;

	private BigDecimal paymentAmount;

	private BigDecimal favoredPaymentAmount;

	private BigDecimal paymentAmountWithRate;

	private BigDecimal discount;

	private BigDecimal addition;

	private PaymentMethodEnum paymentMethod;

	private Integer installments;

	private TransactionStatusEnum status;

	private Date lastStatusUpdate;

	private Customer buyer;

	@SerializedName("pedidos")
	private List<br.com.bcash.domain.transaction.Product> products;

	@SerializedName("frete")
	private String shippingCost;

	@SerializedName("tipo_frete")
	private String shippingType;
	
	@SerializedName("free")
	private String free;

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public PaymentMethodEnum getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethodEnum paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public TransactionStatusEnum getStatus() {
		return status;
	}

	public void setStatus(TransactionStatusEnum status) {
		this.status = status;
	}

	public Customer getBuyer() {
		return buyer;
	}

	public void setBuyer(Customer buyer) {
		this.buyer = buyer;
	}

	public String getSellerEmail() {
		return sellerEmail;
	}

	public void setSellerEmail(String sellerEmail) {
		this.sellerEmail = sellerEmail;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getCreditDate() {
		return creditDate;
	}

	public void setCreditDate(Date creditDate) {
		this.creditDate = creditDate;
	}

	public BigDecimal getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public BigDecimal getFavoredPaymentAmount() {
		return favoredPaymentAmount;
	}

	public void setFavoredPaymentAmount(BigDecimal favoredPaymentAmount) {
		this.favoredPaymentAmount = favoredPaymentAmount;
	}

	public BigDecimal getPaymentAmountWithRate() {
		return paymentAmountWithRate;
	}

	public void setPaymentAmountWithRate(BigDecimal paymentAmountWithRate) {
		this.paymentAmountWithRate = paymentAmountWithRate;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public BigDecimal getAddition() {
		return addition;
	}

	public void setAddition(BigDecimal addition) {
		this.addition = addition;
	}

	public Integer getInstallments() {
		return installments;
	}

	public void setInstallments(Integer installments) {
		this.installments = installments;
	}

	public Date getLastStatusUpdate() {
		return lastStatusUpdate;
	}

	public void setLastStatusUpdate(Date lastStatusUpdate) {
		this.lastStatusUpdate = lastStatusUpdate;
	}

	public List<br.com.bcash.domain.transaction.Product> getProducts() {
		return products;
	}

	public void setProducts(List<br.com.bcash.domain.transaction.Product> products) {
		this.products = products;
	}

	public String getShippingCost() {
		return shippingCost;
	}

	public void setShippingCost(String shippingCost) {
		this.shippingCost = shippingCost;
	}

	public String getShippingType() {
		return shippingType;
	}

	public void setShippingType(String shippingType) {
		this.shippingType = shippingType;
	}

	public String getFree() {
		return free;
	}

	public void setFree(String free) {
		this.free = free;
	}
	
}
