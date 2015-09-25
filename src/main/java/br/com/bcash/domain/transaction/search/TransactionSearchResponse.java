package br.com.bcash.domain.transaction.search;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class TransactionSearchResponse {

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

}

class Product {
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

}
