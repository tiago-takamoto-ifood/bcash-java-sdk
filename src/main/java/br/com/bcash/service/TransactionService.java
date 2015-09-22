package br.com.bcash.service;

import br.com.bcash.domain.transaction.TransactionRequest;
import br.com.bcash.domain.transaction.TransactionResponse;
import br.com.bcash.http.authentication.OAuthCredentials;

public class TransactionService {

	private static final String SERVICE_URL = "createTransaction/json/";

	private OAuthCredentials oAuthCredentials;

	/**
	 * Cria service de transação para realizar chamadas a API Rest de transações carregando as credenciais fornecidas no properties.
	 * 
	 **/
	public TransactionService() {
		this.oAuthCredentials = OAuthCredentials.loadFromProperties();
	}

	/**
	 * Cria service de transação para realizar chamadas a API Rest de transações utilizando as credenciais fornecidas.
	 * 
	 * @param oAuthCredentials
	 */
	public TransactionService(OAuthCredentials oAuthCredentials) {
		this.oAuthCredentials = oAuthCredentials;
	}

	public TransactionResponse create(TransactionRequest request) {
		return null;
	}

}
