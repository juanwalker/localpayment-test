package com.localpayment.application.postActions;

import com.localpayment.domain.entities.Person;

public class AddUser {

	private String name;


	private String personId;
	private String password;
	
	public AddUser(String name, String personId, String password){
		this.name = name;
		this.personId = personId;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}

