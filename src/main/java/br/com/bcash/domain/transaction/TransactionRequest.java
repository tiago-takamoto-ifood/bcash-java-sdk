package br.com.bcash.domain.transaction;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import br.com.bcash.domain.customer.Customer;

import com.google.gson.annotations.SerializedName;

public class TransactionRequest {

	private String sellerMail;

	private String ipSeller;

	private String orderId;

	private Customer buyer;

	private String free;

	@SerializedName("freight")
	private BigDecimal shippingAmount;

	@SerializedName("freightType")
	private String shippingType;

	private BigDecimal discount;

	private BigDecimal addition;

	private PaymentMethod paymentMethod;

	private String urlNotification;

	private List<Product> products;

	private Integer installments;

	private CreditCardRequest creditCard;

	private String currency;

	private String acceptedContract;

	private String viewedContract;

	private Integer campaignId;

	private Integer platformId;

	private List<DependentTransaction> dependentTransactions;

	private String idTransaction;

	private String transactionKey;

	private String origin;

	private Integer salesChannel;

	private String wallet;

	private BigDecimal transactionDiscount;

	private Date maturityDate;

	private String bankSlipDescription;

	/**
	 * Recupera o e-mail da loja cadastrada no Bcash.
	 * 
	 * @return sellerMail
	 */
	public String getSellerMail() {
		return sellerMail;
	}

	/**
	 * E-mail da loja cadastrada no Bcash.<br>
	 * <i>Tamanho m�ximo: 80 caracteres</i>.<br>
	 * <b>Campo obrigat�rio</b>
	 * 
	 * @param sellerMail
	 *            ex.: empresa@provedor.com
	 */
	public void setSellerMail(final String sellerMail) {
		this.sellerMail = sellerMail;
	}

	/**
	 * Recupera o identificador do pedido da sua loja.
	 * 
	 * @return orderId
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * Identificador do pedido atribu�do da sua loja.<br>
	 * <i>Tamanho m�ximo: 50 caracteres</i>
	 * 
	 * @param orderId
	 *            ex.: 123
	 */
	public void setOrderId(final String orderId) {
		this.orderId = orderId;
	}

	/**
	 * Recupera o objeto {@link Customer}.
	 * 
	 * @return buyer
	 */
	public Customer getBuyer() {
		return buyer;
	}

	/**
	 * Objeto {@link Customer}.<br>
	 * <b>Campo obrigat�rio</b>
	 * 
	 * @param buyer
	 */
	public void setBuyer(final Customer buyer) {
		this.buyer = buyer;
	}

	/**
	 * Recupera o campo de Livre Digita��o.
	 * 
	 * @return free
	 */
	public String getFree() {
		return free;
	}

	/**
	 * Campo de Livre Digita��o. Pode ser utilizado para algum par�metro adicional de identifica��o da venda.<br>
	 * <i>Tamanho m�ximo: 255 caracteres</i>
	 * 
	 * @param free
	 *            ex.: compra teste
	 */
	public void setFree(final String free) {
		this.free = free;
	}

	/**
	 * Recupera o valor do frete.
	 * 
	 * @return shippingAmount
	 */
	public BigDecimal getShippingAmount() {
		return shippingAmount;
	}

	/**
	 * Valor do frete.<br>
	 * 
	 * @param shippingAmount
	 *            ex.: new BigDecimal("10.95")
	 */
	public void setShippingAmount(final BigDecimal shippingAmount) {
		this.shippingAmount = shippingAmount;
	}

	/**
	 * Recupera o tipo do frete.
	 * 
	 * @return shippingType
	 */
	public String getShippingType() {
		return shippingType;
	}

	/**
	 * Tipo do frete.<br>
	 * <br>
	 * *Vide enum: {@link FreightTypeEnum}
	 * 
	 * @param shippingType
	 *            ex.: ShippingTypeEnum.SEDEX
	 */
	public void setShippingType(final String shippingType) {
		this.shippingType = shippingType;
	}

	/**
	 * Recupera o valor total do desconto atribu�do pela loja.
	 * 
	 * @return discount
	 */
	public BigDecimal getDiscount() {
		return discount;
	}

	/**
	 * Valor total do desconto atribu�do pela loja.<br>
	 * 
	 * 
	 * @param discount
	 *            ex.: new BigDecimal("2.25")
	 */
	public void setDiscount(final BigDecimal discount) {
		this.discount = discount;
	}

	/**
	 * Recupera o valor total do acr�scimo feito pela loja.
	 * 
	 * @return addition
	 */
	public BigDecimal getAddition() {
		return addition;
	}

	/**
	 * Valor total do acr�scimo feito pela loja.
	 * 
	 * @param addition
	 *            ex.: new BigDecimal("2.25")
	 */
	public void setAddition(final BigDecimal addition) {
		this.addition = addition;
	}

	/**
	 * Recupera o objeto {@link PaymentMethod}.
	 * 
	 * @return paymentMethod
	 */
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	/**
	 * Objeto {@link PaymentMethod}.<br>
	 * <b>Campo obrigat�rio</b>
	 * 
	 * @param paymentMethod
	 */
	public void setPaymentMethod(final PaymentMethodEnum paymentMethod) {
		this.paymentMethod = new PaymentMethod(paymentMethod.getCode());
	}

	/**
	 * Recupera a URL que a loja ir receber as informa��es de altera��o de status da transa��o.
	 * 
	 * @return urlNotification
	 */
	public String getUrlNotification() {
		return urlNotification;
	}

	/**
	 * URL que a loja ir receber as informa��es de altera��o de status da transa��o.<br>
	 * <i>Tamanho m�ximo: 255 caracteres</i>
	 * 
	 * 
	 * @param urlNotification
	 *            , http://www.bcash.com.br/loja/aviso.php
	 */
	public void setUrlNotification(final String urlNotification) {
		this.urlNotification = urlNotification;
	}

	/**
	 * Recupera a lista de produtos.
	 * 
	 * @return products
	 */
	public List<Product> getProducts() {
		return products;
	}

	/**
	 * Lista de produtos.<br>
	 * <b>Campo obrigat�rio</b>.<br>
	 * 
	 * *Obs.: Preencher a lista com o objeto {@link Product}
	 * 
	 * @param products
	 */
	public void setProducts(final List<Product> products) {
		this.products = products;
	}

	/**
	 * Recupera a quantidade de Parcelas que a compra ser� processada.
	 * 
	 * @return installments
	 */
	public Integer getInstallments() {
		return installments;
	}

	/**
	 * Quantidade de Parcelas que a compra ser� processada.<br>
	 * 
	 * @param installments
	 *            ex.: 5
	 */
	public void setInstallments(final Integer installments) {
		this.installments = installments;
	}

	/**
	 * Recupera o objeto {@link CreditCardRequest}.
	 * 
	 * @return creditCard
	 */
	public CreditCardRequest getCreditCard() {
		return creditCard;
	}

	/**
	 * Objeto {@link CreditCardRequest}.
	 * 
	 * @param creditCard
	 */
	public void setCreditCard(final CreditCardRequest creditCard) {
		this.creditCard = creditCard;
	}

	/**
	 * Recupera a moeda utilizada para a transa��o.
	 * 
	 * @return currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * Moeda utilizada para a transa��o.<br>
	 * <i>Tamanho m�ximo: 3 caracteres</i>.<br>
	 * <br>
	 * 
	 * *Vide enum: {@link CurrencyEnum}
	 * 
	 * @param currency
	 *            ex.: CurrencyEnum.REAL
	 */
	public void setCurrency(final String currency) {
		this.currency = currency;
	}

	/**
	 * Recupera se a loja informou o comprador aceitou os termos do contrato.
	 * 
	 * @return acceptedContract
	 */
	public Boolean getAcceptedContract() {
		return acceptedContract == null || acceptedContract.isEmpty() || !acceptedContract.equals("S") ? false : true;
	}

	/**
	 * Loja informa se o comprador aceitou os termos do contrato.<br>
	 * 
	 * @param acceptedContract
	 *            ex.: Boolean.TRUE
	 */
	public void setAcceptedContract(final Boolean acceptedContract) {
		this.acceptedContract = acceptedContract ? "S" : "N";
	}

	/**
	 * Recupera se a loja informou o comprador visualizou os termos do contrato.
	 * 
	 * @return viewContract
	 */
	public Boolean getViewedContract() {
		return viewedContract == null || viewedContract.isEmpty() || !viewedContract.equals("S") ? false : true;
	}

	/**
	 * Loja informa se o comprador visualizou os termos do contrato.<br>
	 * 
	 * @param viewedContract
	 *            ex.: Boolean.TRUE
	 */
	public void setViewedContract(final Boolean viewedContract) {
		this.viewedContract = viewedContract ? "S" : "N";
	}

	/**
	 * Recupera o identificador da campanha da loja no Pagamento Digital.
	 * 
	 * @return campaignId
	 */
	public Integer getCampaignId() {
		return campaignId;
	}

	/**
	 * Identificador da campanha da loja no Pagamento Digital.
	 * 
	 * @param campaignId
	 *            ex.: 123
	 */
	public void setCampaignId(final Integer campaignId) {
		this.campaignId = campaignId;
	}

	/**
	 * Recupera o identificador da plataforma no Pagamento Digital
	 * 
	 * @return platformId
	 */
	public Integer getPlatformId() {
		return platformId;
	}

	/**
	 * Identificador da plataforma no Pagamento Digital.
	 * 
	 * @param platformId
	 */
	public void setPlatformId(final Integer platformId) {
		this.platformId = platformId;
	}

	/**
	 * Recupera o endere�o de IP da loja.
	 * 
	 * @return ipSeller
	 */
	public String getIpSeller() {
		return ipSeller;
	}

	/**
	 * Endere�o de IP da loja.<br>
	 * <i>Tamanho m�ximo: 40 caracteres</i>.<br>
	 * <b>Campo obrigat�rio</b>
	 * 
	 * @param ipSeller
	 *            ex.: 169.254.57.175
	 */
	public void setIpSeller(final String ipSeller) {
		this.ipSeller = ipSeller;
	}

	/**
	 * Recupera a lista de transa��es dependentes.
	 * 
	 * @return List<{@link DependentTransaction}>
	 */
	public List<DependentTransaction> getDependentTransactions() {
		return dependentTransactions;
	}

	/**
	 * Lista de transa��es dependentes.<br>
	 * 
	 * *Obs.: Preencher a lista com o objeto {@link DependentTransaction}
	 * 
	 * @param transactionDependents
	 *            List<{@link DependentTransaction}>
	 */
	public void setDependentTransactions(final List<DependentTransaction> dependentTransactions) {
		this.dependentTransactions = dependentTransactions;
	}

	public String getIdTransaction() {
		return idTransaction;
	}

	public void setIdTransaction(final String idTransaction) {
		this.idTransaction = idTransaction;
	}

	public String getTransactionKey() {
		return transactionKey;
	}

	public void setTransactionKey(final String transactionKey) {
		this.transactionKey = transactionKey;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(final String origin) {
		this.origin = origin;
	}

	public Integer getSalesChannel() {
		return salesChannel;
	}

	public void setSalesChannel(final Integer salesChannel) {
		this.salesChannel = salesChannel;
	}

	/**
	 * @return Obt�m o token da carteira Bcash
	 */
	public String getWallet() {
		return wallet;
	}

	/**
	 * @param wallet
	 *            Token da carteira Bcash
	 */
	public void setWallet(String wallet) {
		this.wallet = wallet;
	}

	public BigDecimal getTransactionDiscount() {
		return transactionDiscount;
	}

	public void setTransactionDiscount(final BigDecimal transactionDiscount) {
		this.transactionDiscount = transactionDiscount;
	}

	public Date getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(final Date maturityDate) {
		this.maturityDate = maturityDate;
	}

	public String getBankSlipDescription() {
		return bankSlipDescription;
	}

	public void setBankSlipDescription(String bankSlipDescription) {
		this.bankSlipDescription = bankSlipDescription;
	}

}
