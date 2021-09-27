package com.localpayment.application.controllers;

import com.localpayment.application.actionsResponses.PersonResponse;
import com.localpayment.application.actionsResponses.ResponseStatus;
import com.localpayment.application.postActions.AddPerson;
import com.localpayment.application.services.PersonService;
import com.localpayment.domain.entities.Person;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class PersonController {

	private final PersonService personService;


	public PersonController(PersonService personService) {
		this.personService = personService;
	}

	@GetMapping("/{id}")
	public PersonResponse get(@PathVariable(name = "id") String  id) {
		Person person = this.personService.findById(id);
		if (person != null) {
			return new PersonResponse(person,"Person found", ResponseStatus.OK.getMessage());
		} else {
			return new PersonResponse(null,"Person not found", ResponseStatus.KO.getMessage());

		}

	}

	@PostMapping("/")
		public PersonResponse add(@RequestBody AddPerson addPerson) {
		Person person = this.personService.add(addPerson);
		if (person != null) {
			return new PersonResponse(person,"Person created", ResponseStatus.OK.getMessage());
		} else {
			return new PersonResponse(person,"Person not created", ResponseStatus.KO.getMessage());
		}
	}

	
	
}
