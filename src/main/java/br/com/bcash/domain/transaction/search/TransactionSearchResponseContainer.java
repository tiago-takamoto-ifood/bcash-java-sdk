package br.com.bcash.domain.transaction.search;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class TransactionSearchResponseContainer {

	@SerializedName("transacao")
	private Transaction transaction;

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	
	public class Transaction {

		@SerializedName("id_transacao")
		private String transactionId;

		@SerializedName("id_pedido")
		private String orderId;

		@SerializedName("email_vendedor")
		private String sellerEmail;

		@SerializedName("data_transacao")
		private Date createdDate;

		@SerializedName("data_credito")
		private Date creditDate;

		@SerializedName("valor_original")
		private BigDecimal paymentAmount;

		@SerializedName("valor_loja")
		private BigDecimal favoredPaymentAmount;

		@SerializedName("valor_total")
		private BigDecimal paymentAmountWithRate;

		@SerializedName("desconto")
		private BigDecimal discount;

		@SerializedName("acrescimo")
		private BigDecimal addition;

		@SerializedName("cod_meio_pagamento")
		private Integer paymentMethodId;

		@SerializedName("meio_pagamento")
		private String paymentMethodDescription;

		@SerializedName("parcelas")
		private Integer installments;

		@SerializedName("cod_status")
		private Integer statusId;

		@SerializedName("status")
		private String statusDescription;

		@SerializedName("data_alteracao_status")
		private Date lastStatusUpdate;

		@SerializedName("cliente_razao_social")
		private String companyName;

		@SerializedName("cliente_nome_fantasia")
		private String businessName;

		@SerializedName("cliente_nome")
		private String name;

		@SerializedName("cliente_email")
		private String email;

		@SerializedName("cliente_rg")
		private String rg;

		@SerializedName("cliente_cnpj")
		private String cnpj;

		@SerializedName("cliente_cpf")
		private String cpf;

		@SerializedName("cliente_telefone")
		private String phone;

		@SerializedName("cliente_endereco")
		private String address;

		@SerializedName("cliente_complemento")
		private String complement;

		@SerializedName("cliente_bairro")
		private String neighborhood;

		@SerializedName("cliente_cidade")
		private String city;

		@SerializedName("cliente_estado")
		private String state;

		@SerializedName("cliente_cep")
		private String zipCode;

		@SerializedName("pedidos")
		private List<Product> products;

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

		public Integer getPaymentMethodId() {
			return paymentMethodId;
		}

		public void setPaymentMethodId(Integer paymentMethodId) {
			this.paymentMethodId = paymentMethodId;
		}

		public String getPaymentMethodDescription() {
			return paymentMethodDescription;
		}

		public void setPaymentMethodDescription(String paymentMethodDescription) {
			this.paymentMethodDescription = paymentMethodDescription;
		}

		public Integer getInstallments() {
			return installments;
		}

		public void setInstallments(Integer installments) {
			this.installments = installments;
		}

		public Integer getStatusId() {
			return statusId;
		}

		public void setStatusId(Integer statusId) {
			this.statusId = statusId;
		}

		public String getStatusDescription() {
			return statusDescription;
		}

		public void setStatusDescription(String statusDescription) {
			this.statusDescription = statusDescription;
		}

		public Date getLastStatusUpdate() {
			return lastStatusUpdate;
		}

		public void setLastStatusUpdate(Date lastStatusUpdate) {
			this.lastStatusUpdate = lastStatusUpdate;
		}

		public String getCompanyName() {
			return companyName;
		}

		public void setCompanyName(String companyName) {
			this.companyName = companyName;
		}

		public String getBusinessName() {
			return businessName;
		}

		public void setBusinessName(String businessName) {
			this.businessName = businessName;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getRg() {
			return rg;
		}

		public void setRg(String rg) {
			this.rg = rg;
		}

		public String getCnpj() {
			return cnpj;
		}

		public void setCnpj(String cnpj) {
			this.cnpj = cnpj;
		}

		public String getCpf() {
			return cpf;
		}

		public void setCpf(String cpf) {
			this.cpf = cpf;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getComplement() {
			return complement;
		}

		public void setComplement(String complement) {
			this.complement = complement;
		}

		public String getNeighborhood() {
			return neighborhood;
		}

		public void setNeighborhood(String neighborhood) {
			this.neighborhood = neighborhood;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public String getZipCode() {
			return zipCode;
		}

		public void setZipCode(String zipCode) {
			this.zipCode = zipCode;
		}

		public List<Product> getProducts() {
			return products;
		}

		public void setProducts(List<Product> products) {
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
	
	public class Product {
		@SerializedName("codigo_produto")
		private String code;

		@SerializedName("nome_produto")
		private String description;

		@SerializedName("qtde")
		private Integer amount;

		@SerializedName("valor_total")
		private BigDecimal value;

		@SerializedName("extra")
		private String extraDescription;

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public Integer getAmount() {
			return amount;
		}

		public void setAmount(Integer amount) {
			this.amount = amount;
		}

		public BigDecimal getValue() {
			return value;
		}

		public void setValue(BigDecimal value) {
			this.value = value;
		}

		public String getExtraDescription() {
			return extraDescription;
		}

		public void setExtraDescription(String extraDescription) {
			this.extraDescription = extraDescription;
		}

	}

}