package com.localpayment.application.services;

import com.localpayment.application.postActions.AddCard;
import com.localpayment.domain.entities.Card;
import com.localpayment.domain.entities.Person;
import com.localpayment.domain.repositories.CardRepository;
import com.localpayment.domain.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class CardService {
	@Autowired
	private CardRepository cardRepository;
	@Autowired
	private PersonRepository personRepository;

    public CardService(CardRepository cardRepository,	 PersonRepository personRepository) {
        this.cardRepository = cardRepository;
        this.personRepository = personRepository;
    }

	private Double SQUARate() {
		Date today = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);
		Integer currentMonth = cal.get(Calendar.MONTH) + 1;
		Integer currentYear = cal.get(Calendar.YEAR);
		return  Double.valueOf(currentYear / currentMonth);
	}

	private Double SCORate() {
		Date today = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);
		int currentDayMonth = cal.get(Calendar.DAY_OF_MONTH);
		return  currentDayMonth * 0.5;
	}

	private Double PERERate() {
		Date today = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);
		int currentMonth = cal.get(Calendar.MONTH) + 1;
		return  currentMonth * 0.1;
	}

	private Double getRate(String name) {

		String upperCaseName = name.toUpperCase();

		if (upperCaseName.equals("SQUA")){
			return this.SQUARate();
		} else if (upperCaseName.equals("SQUA")){
			return this.SCORate();
		} else if (upperCaseName.equals("PERE")){
			return this.PERERate();
		} else {
			return null;
		}

	}

	public Card findById(String strId) {
		Integer id = Integer.parseInt(strId);
		try {
			return this.cardRepository.findById(id).get();
		} catch (NoSuchElementException e) {
			return null;
		}

	}
	public List<Card> findByPersonId(String strId) {
		Integer userId = Integer.parseInt(strId);
		return this.cardRepository.findByPersonId(userId);

	}


	public Card add(AddCard data){
		Integer id = Integer.parseInt(data.getPersonId());
		try {
			Double rate = this.getRate(data.getName());
			if (rate != null) {
				Person person = personRepository.findById(id).get();
				Card card = new Card(null, person.getName(), data.getNumber(), data.getHolder(), person, data.getLimit(), rate,data.getExpireDate());
				return cardRepository.save(card);
			} else {
				return null;
			}

		} catch (NoSuchElementException e) {
			return null;
		}
	}	
}
