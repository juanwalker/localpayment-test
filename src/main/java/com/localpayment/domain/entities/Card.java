package com.localpayment.domain.entities;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

import static com.fasterxml.jackson.annotation.JsonFormat.*;

@Entity(name="Card")
@JsonIgnoreProperties({"person"})
public class Card {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String number;
	@Column(nullable = false)
	private String holder;
	@JsonProperty("person")
	@ManyToOne(optional = false, cascade = {CascadeType.MERGE},fetch = FetchType.LAZY)
	@JoinColumn(name="PERSON_ID")
	private Person person;
	@Column(nullable = false)
	private Double limitAmount;
	@Column(nullable = false)
	private Double rate;
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
	private Date expireDate;

	public Card() {

	}

	public Card(Integer id,String name, String number, String holder, Person person, Double limitAmount, Double rate,Date expireDate) {
		this.id=id;
		this.name = name;
		this.number = number;
		this.holder = holder;
		this.person = person;
		this.limitAmount = limitAmount;
		this.rate = rate;
		this.expireDate = expireDate;
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
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Double getLimitAmount() {
		return limitAmount;
	}

	public void setLimitAmount(Double limitAmount) {
		this.limitAmount = limitAmount;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Card card = (Card) o;
		return Objects.equals(name, card.name) &&
				Objects.equals(number, card.number) &&
				Objects.equals(holder, card.holder) &&
				Objects.equals(limitAmount, card.limitAmount) &&
				Objects.equals(expireDate, card.expireDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, number, holder, limitAmount, expireDate);
	}
}
