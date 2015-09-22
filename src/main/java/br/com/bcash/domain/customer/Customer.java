package br.com.bcash.domain.customer;

import java.util.Date;

public class Customer {

	private String mail;

	private String name;

	private String cpf;

	private String phone;

	private String cellPhone;

	private Address address;

	private Character gender;

	private Date birthDate;

	private String rg;

	private Date issueRgDate;

	private String organConsignorRg;

	private String stateConsignorRg;

	private String companyName;

	private String cnpj;

	private String searchToken;

	private String ipAddress;

	private String deviceFingerprint;

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Character getGender() {
		return gender;
	}

	public void setGender(Character gender) {
		this.gender = gender;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public Date getIssueRgDate() {
		return issueRgDate;
	}

	public void setIssueRgDate(Date issueRgDate) {
		this.issueRgDate = issueRgDate;
	}

	public String getOrganConsignorRg() {
		return organConsignorRg;
	}

	public void setOrganConsignorRg(String organConsignorRg) {
		this.organConsignorRg = organConsignorRg;
	}

	public String getStateConsignorRg() {
		return stateConsignorRg;
	}

	public void setStateConsignorRg(String stateConsignorRg) {
		this.stateConsignorRg = stateConsignorRg;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getSearchToken() {
		return searchToken;
	}

	public void setSearchToken(String searchToken) {
		this.searchToken = searchToken;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getDeviceFingerprint() {
		return deviceFingerprint;
	}

	public void setDeviceFingerprint(String deviceFingerprint) {
		this.deviceFingerprint = deviceFingerprint;
	}

}
