package br.com.bcash.domain.transaction.search;

import java.math.BigDecimal;
import java.util.Date;

import com.google.gson.annotations.SerializedName;

public class TransactionSearchResponse {

	@SerializedName("id_transacao")
	private String transactionId;

	@SerializedName("data_transacao")
	private Date createdDate;

	@SerializedName("data_credito")
	private Date creditDate;

	@SerializedName("data_credito")
	private BigDecimal paymentAmount;

}
