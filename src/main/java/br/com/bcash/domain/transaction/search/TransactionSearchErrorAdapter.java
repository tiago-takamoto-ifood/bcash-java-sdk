package br.com.bcash.domain.transaction.search;

import java.util.Arrays;

import br.com.bcash.domain.error.ErrorList;
import br.com.bcash.domain.error.ResponseError;

public class TransactionSearchErrorAdapter {

	public static ErrorList adapt(SearchError error) {
		ErrorList errorList = new ErrorList();

		ResponseError responseError = new ResponseError();
		responseError.setCode(error.getError().getCode());
		responseError.setDescription(error.getError().getDescription());

		errorList.setList(Arrays.asList(responseError));
		
		return errorList;
	}
	
}
