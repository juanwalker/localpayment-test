package com.localpayment.application.services;


import com.localpayment.application.postActions.AddPerson;
import com.localpayment.domain.entities.Person;
import com.localpayment.domain.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class PersonService {
	@Autowired
	private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    public Person findById(String strId) {
    	Integer id =Integer.parseInt(strId);
    	System.out.println(id);
    	try {
			return this.personRepository.findById(id).get();
		} catch (NoSuchElementException e) {
    		return null;
		}


	}

	public Person add(AddPerson data){
		if (data != null) {
			Person person = new Person(null, data.getName(), data.getSurname(), data.getAddress(),data.getIdNumber(),null);
			return personRepository.save(person);
		} else {
			return null;
		}
	}	
}
