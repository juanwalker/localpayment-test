package com.localpayment.application.postActions;

public class AddPerson {


	private String name;
	private String surname;
	private String address;
	private String idNumber;

	public AddPerson( String name, String surname, String address,String idNumber){

		this.name = name;
		this.surname = surname;
		this.address = address;
		this.idNumber = idNumber;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
}

