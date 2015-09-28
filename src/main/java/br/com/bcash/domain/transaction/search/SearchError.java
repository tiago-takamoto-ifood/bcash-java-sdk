package br.com.bcash.domain.transaction.search;

import com.google.gson.annotations.SerializedName;

public class SearchError {

	@SerializedName("erro")
	private Error error;

	public Error getError() {
		return error;
	}

	public void setError(Error error) {
		this.error = error;
	}

	public class Error {

		@SerializedName("codigo")
		private String code;

		@SerializedName("descricao")
		private String description;

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

	} 
	
}