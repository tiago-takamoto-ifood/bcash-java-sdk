package br.com.bcash.domain.customer;

public class Address {

	public static final String WITHOUT_NUMBER = "S/N";

	private String address;

	private String number;

	private String complement;

	private String neighborhood;

	private String city;

	private String state;

	private String zipCode;

	/**
	 * Recupera o endere�o do comprador.
	 * 
	 * @return address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Endere�o do comprador.<br>
	 * <i>Tamanho m�ximo: 100 caracteres</i>.<br>
	 * <b>Campo obrigat�rio</b>
	 * 
	 * @param address
	 *            ex.: Av. Tiradentes
	 */
	public void setAddress(final String address) {
		this.address = address;
	}

	/**
	 * Recupera o n�mero do endere�o.
	 * 
	 * @return number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * N�mero do endere�o.<br>
	 * Caso o endere�o informado n�o possua n�mero informar S/N <i>Tamanho m�ximo: 10 caracteres</i>.<br>
	 * <b>Campo obrigat�rio</b>
	 * 
	 * @param number
	 *            ex.: 1254
	 */
	public void setNumber(final String number) {
		this.number = number;
	}

	/**
	 * Recupera o complemento do endere�o do comprador.
	 * 
	 * @return complement
	 */
	public String getComplement() {
		return complement;
	}

	/**
	 * Complemento do endere�o do comprador.<br>
	 * <i>Tamanho m�ximo: 80 caracteres</i>.<br>
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
	 * <i>Tamanho m�ximo: 50 caracteres</i>.<br>
	 * <b>Campo obrigat�rio</b>
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
	 * <i>Tamanho m�ximo: 255 caracteres</i>.<br>
	 * <b>Campo obrigat�rio</b>
	 * 
	 * @param city
	 *            ex.: S�o Paulo
	 */
	public void setCity(final String city) {
		this.city = city;
	}

	/**
	 * Recupera o estado do comprador.
	 * 
	 * @return state
	 */
	public StateEnum getState() {
		try {
			return StateEnum.fromAbbreviation(state);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	/**
	 * Estado do comprador.<br>
	 * <i>Tamanho m�ximo: 2 caracteres</i>.<br>
	 * <b>Campo obrigat�rio</b>.<br>
	 * <br>
	 * *Vide enum: {@link StateEnum}
	 * 
	 * @param state
	 *            ex.: StateEnum.MINAS_GERAIS
	 */
	public void setState(final StateEnum state) {
		this.state = state.getAbbreviation();
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
	 * <i>Tamanho m�ximo: 9 caracteres</i>.<br>
	 * <b>Campo obrigat�rio</b>
	 * 
	 * @param zipCode
	 *            ex.: 17500000
	 */
	public void setZipCode(final String zipCode) {
		this.zipCode = zipCode;
	}

}
