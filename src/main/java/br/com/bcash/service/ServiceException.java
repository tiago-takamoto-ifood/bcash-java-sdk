package br.com.bcash.service;

import java.util.Collections;
import java.util.List;

import br.com.bcash.domain.error.ErrorList;
import br.com.bcash.domain.error.ResponseError;

public class ServiceException extends Exception {

	private static final long serialVersionUID = 157616301864856056L;

	private List<ResponseError> errors = Collections.emptyList();

	public ServiceException(final ErrorList errors) {
		this.errors = errors.getList();
	}

	public ServiceException(final List<ResponseError> errors) {
		this.errors = errors;
	}

	/**
	 * Lista de erros retornada pelo serviço.
	 * 
	 * @return List<@{link ResponseError}>
	 */
	public List<ResponseError> getErrors() {
		return errors;
	}
}
