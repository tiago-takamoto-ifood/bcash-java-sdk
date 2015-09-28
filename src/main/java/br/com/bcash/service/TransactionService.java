package br.com.bcash.service;

import java.io.IOException;

import br.com.bcash.domain.transaction.TransactionRequest;
import br.com.bcash.domain.transaction.TransactionResponse;
import br.com.bcash.domain.transaction.cancel.TransactionCancelResponse;
import br.com.bcash.domain.transaction.search.TransactionSearchResponse;
import br.com.bcash.http.authentication.BasicCredentials;
import br.com.bcash.http.authentication.OAuthCredentials;

public class TransactionService {

	private final OAuthCredentials oAuthCredentials;

	private final BasicCredentials basicCredentials;

	/**
	 * Cria service de transação para realizar chamadas a API Rest de transações carregando as credenciais fornecidas no properties.
	 * 
	 **/
	public TransactionService() {
		this.oAuthCredentials = OAuthCredentials.loadFromProperties();
		this.basicCredentials = BasicCredentials.loadFromProperties();
	}

	/**
	 * Cria service de transação para realizar chamadas a API Rest de transações utilizando as credenciais fornecidas. As credenciais OAuth
	 * são utilizadas para os serviços de criação de transação.
	 * 
	 * @param oAuthCredentials
	 */
	public TransactionService(OAuthCredentials oAuthCredentials) {
		this.oAuthCredentials = oAuthCredentials;
		this.basicCredentials = null;
	}

	/**
	 * Cria service de transação para realizar chamadas a API Rest de transações utilizando as credenciais fornecidas. As credenciais Basic
	 * são utilizadas para consumir os serviços da API Rest.
	 * 
	 * @param oAuthCredentials
	 */
	public TransactionService(BasicCredentials basicCredentials) {
		this.oAuthCredentials = null;
		this.basicCredentials = basicCredentials;
	}

	/**
	 * Cria service de transação para realizar chamadas a API Rest de transações utilizando as credenciais fornecidas. As credenciais OAuth
	 * são utilizadas para os serviços de criação de transação. As credenciais Basic são utilizadas para consumir os demais serviços da API
	 * Rest.
	 * 
	 * @param oAuthCredentials
	 */
	public TransactionService(OAuthCredentials oAuthCredentials, BasicCredentials basicCredentials) {
		this.oAuthCredentials = oAuthCredentials;
		this.basicCredentials = basicCredentials;
	}

	/**
	 * Realiza a comunicação com o serviço de criação de transações com base nos dados informados.
	 * 
	 * @param request
	 *            Dados da transação
	 * @return
	 * @throws ServiceException
	 *             Caso o serviço retorne um erro.
	 * @throws IOException
	 *             Caso ocorra algum problema na comunicação.
	 */
	public TransactionResponse create(TransactionRequest request) throws IOException, ServiceException {
		return new TransactionCreateService(oAuthCredentials).create(request);
	}

	/**
	 * Realiza o cancelamento da transação Bcash informada.
	 * 
	 * @param transactionId
	 *            Identificador da transação Bcash.
	 * @return
	 * @throws ServiceException
	 *             Caso o serviço retorne um erro.
	 * @throws IOException
	 *             Caso ocorra algum problema na comunicação.
	 */
	public TransactionCancelResponse cancel(String transactionId) throws IOException, ServiceException {
		return new TransactionCancelService(basicCredentials).cancel(transactionId);
	}

	/**
	 * Recupera os dados da transação por meio do identificador da transação Bcash informado.
	 * 
	 * @param transactionId
	 *            Identificador da transação Bcash
	 * @throws ServiceException
	 *             Caso o serviço retorne um erro.
	 * @throws IOException
	 *             Caso ocorra algum problema na comunicação.
	 */
	public TransactionSearchResponse searchById(String transactionId) throws IOException, ServiceException {
		return new TransactionSearchService(basicCredentials).searchById(transactionId);
	}

	/**
	 * Recupera os dados da transação por meio do identificador da da loja enviado ao Bcash.
	 * 
	 * @param transactionId
	 *            Identificador do pedido da loja enviado ao Bcash
	 * @throws ServiceException
	 *             Caso o serviço retorne um erro.
	 * @throws IOException
	 *             Caso ocorra algum problema na comunicação.
	 */
	public TransactionSearchResponse searchByOrderId(String orderId) throws IOException, ServiceException {
		return new TransactionSearchService(basicCredentials).searchByOrderId(orderId);
	}

}
