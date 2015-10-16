package br.com.bcash.domain.transaction;

import java.math.BigDecimal;

public class Product {

	private String code;

	private String description;

	private Integer amount;

	private BigDecimal value;

	private String extraDescription;

	private Integer height;

	private Integer width;

	private Integer length;

	private Integer weight;

	private String establishment;

	private String category;

	/**
	 * Recupera o código que identifica o produto em sua loja.
	 * 
	 * @return code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Código que identifica o produto em sua loja.<br>
	 * <i>Tamanho máximo: 50 caracteres</i>.<br>
	 * <b>Campo obrigatório</b>
	 * 
	 * @param code
	 *            ex.: 446
	 */
	public void setCode(final String code) {
		this.code = code;
	}

	/**
	 * Recupera o descrição ou nome do(s) produto comprado.
	 * 
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Descrição ou nome do(s) produto comprado. Será visualizada pelo cliente.<br>
	 * <i>Tamanho máximo: 255 caracteres</i>.<br>
	 * <b>Campo obrigatório</b>
	 * 
	 * @param description
	 *            ex.: Camiseta da seleção brasileira
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * Recupera o quantidade comprada deste produto.
	 * 
	 * @return amount
	 */
	public Integer getAmount() {
		return amount;
	}

	/**
	 * Quantidade comprada deste produto.<br>
	 * <b>Campo obrigatório</b>
	 * 
	 * @param amount
	 *            ex.: 2
	 */
	public void setAmount(final Integer amount) {
		this.amount = amount;
	}

	/**
	 * Recupera o valor unitário do produto.
	 * 
	 * @return value
	 */
	public BigDecimal getValue() {
		return value;
	}

	/**
	 * Valor unitário do produto.<br>
	 * <b>Campo obrigatório</b>
	 * 
	 * @param value
	 *            ex.: new BigDecimal("10.95")
	 */
	public void setValue(final BigDecimal value) {
		this.value = value;
	}

	/**
	 * Recupera o descrição adicional do produto.
	 * 
	 * @return extraDescription
	 */
	public String getExtraDescription() {
		return extraDescription;
	}

	/**
	 * Descrição adicional do produto.<br>
	 * <i>Tamanho máximo: 255 caracteres</i>
	 * 
	 * @param extraDescription
	 *            ex.: Tamanho P
	 */
	public void setExtraDescription(final String extraDescription) {
		this.extraDescription = extraDescription;
	}

	/**
	 * Recupera o a altura do produto.
	 * 
	 * @return value
	 */
	public Integer getHeight() {
		return height;
	}

	/**
	 * Altura o produto em centímetros.<br>
	 * 
	 * @param height
	 *            ex.: 10
	 */
	public void setHeight(Integer height) {
		this.height = height;
	}

	/**
	 * Recupera o largura do produto.
	 * 
	 * @return value
	 */
	public Integer getWidth() {
		return width;
	}

	/**
	 * Largura o produto em centímetros.<br>
	 * 
	 * @param width
	 *            ex.: 10
	 */
	public void setWidth(Integer width) {
		this.width = width;
	}

	/**
	 * Recupera o comprimento do produto.
	 * 
	 * @return value
	 */
	public Integer getLength() {
		return length;
	}

	/**
	 * Comprimento o produto em centímetros.<br>
	 * 
	 * @param length
	 *            ex.: 10
	 */
	public void setLength(Integer length) {
		this.length = length;
	}

	/**
	 * Recupera o o peso do produto.
	 * 
	 * @return value
	 */
	public Integer getWeight() {
		return weight;
	}

	/**
	 * Peso o produto em gramas.<br>
	 * 
	 * @param weight
	 *            ex.: 500
	 */
	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getEstablishment() {
		return establishment;
	}

	public void setEstablishment(String establishment) {
		this.establishment = establishment;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
