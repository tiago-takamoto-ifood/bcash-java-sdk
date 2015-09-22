package br.com.bcash.domain.customer;

public class Address {

	private String address;

	private String number;

	private String complement;

	private String neighborhood;

	private String city;

	private String state;

	private String zipCode;

	/**
	 * Recupera o endereço do comprador.
	 * 
	 * @return address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Endereço do comprador.<br>
	 * <i>Tamanho máximo: 100 caracteres</i>.<br>
	 * <b>Campo obrigatório</b>
	 * 
	 * @param address
	 *            ex.: Av. Tiradentes
	 */
	public void setAddress(final String address) {
		this.address = address;
	}

	/**
	 * Recupera o número do endereço.
	 * 
	 * @return number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * Número do endereço.<br>
	 * Caso o endereço informado não possua número informar S/N
	 * <i>Tamanho máximo: 10 caracteres</i>.<br>
	 * <b>Campo obrigatório</b>
	 * 
	 * @param number
	 *            ex.: 1254
	 */
	public void setNumber(final String number) {
		this.number = number;
	}

	/**
	 * Recupera o complemento do endereço do comprador.
	 * 
	 * @return complement
	 */
	public String getComplement() {
		return complement;
	}

	/**
	 * Complemento do endereço do comprador.<br>
	 * <i>Tamanho máximo: 80 caracteres</i>.<br>
	 * 
	 * @param complement
	 *            ex.: Apartamento 10
	 */
	public void setComplement(final String complement) {
		this.complement = complement;
	}

	/**
	 * Recupera o bairro do comprador.
	 * 
	 * @return neighborhood
	 */
	public String getNeighborhood() {
		return neighborhood;
	}

	/**
	 * Bairro do comprador.<br>
	 * <i>Tamanho máximo: 50 caracteres</i>.<br>
	 * <b>Campo obrigatório</b>
	 * 
	 * @param neighborhood
	 *            ex.: Centro
	 */
	public void setNeighborhood(final String neighborhood) {
		this.neighborhood = neighborhood;
	}

	/**
	 * Recupera o cidade do comprador.
	 * 
	 * @return city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Cidade do comprador.<br>
	 * <i>Tamanho máximo: 255 caracteres</i>.<br>
	 * <b>Campo obrigatório</b>
	 * 
	 * @param city
	 *            ex.: São Paulo
	 */
	public void setCity(final String city) {
		this.city = city;
	}

	/**
	 * Recupera o estado do comprador.
	 * 
	 * @return state
	 */
	public String getState() {
		return state;
	}

	/**
	 * Estado do comprador.<br>
	 * <i>Tamanho máximo: 2 caracteres</i>.<br>
	 * <b>Campo obrigatório</b>.<br>
	 * <br>
	 * *Vide enum: {@link StateEnum}
	 * 
	 * @param state
	 *            ex.: StateEnum.MINAS_GERAIS
	 */
	public void setState(final String state) {
		this.state = state;
	}

	/**
	 * Recupera o CEP do comprador.
	 * 
	 * @return zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * CEP do comprador.<br>
	 * <i>Tamanho máximo: 9 caracteres</i>.<br>
	 * <b>Campo obrigatório</b>
	 * 
	 * @param zipCode
	 *            ex.: 17500000
	 */
	public void setZipCode(final String zipCode) {
		this.zipCode = zipCode;
	}
	
}
