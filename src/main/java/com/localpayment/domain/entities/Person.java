package com.localpayment.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@JsonIgnoreProperties({"cards"})
@Entity(name="Person")
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String surname;
	@Column(nullable = false)
	private String address;
	@Column(nullable = false)
	private String idNumber;
	
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderColumn(name = "id",nullable = false)
	@JsonProperty("cards")
	private Card[] cards;

    public Person(){

	}

	public Person(Integer id, String name, String surname, String address,String idNumber, Card[] cards){
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.idNumber = idNumber;
		this.cards = cards;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Card[] getCards() {
		return cards;
	}

	public void setCards(Card[] cards) {
		this.cards = cards;
	}

}
