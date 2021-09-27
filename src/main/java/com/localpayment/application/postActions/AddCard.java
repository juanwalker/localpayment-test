package com.localpayment.application.postActions;


import java.util.Date;

public class AddCard {


	private String name;

	private String number;

	private String holder;

	private String personId;

	private Double limit;

	private Date expireDate;

	public AddCard(String name, String number, String holder, String personId, Double limit) {
		this.name = name;
		this.number = number;
		this.holder = holder;
		this.personId = personId;
		this.limit = limit;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getHolder() {
		return holder;
	}

	public void setHolder(String holder) {
		this.holder = holder;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public Double getLimit() {
		return limit;
	}

	public void setLimit(Double limit) {
		this.limit = limit;
	}



	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
}

