package com.localpayment.application.actionsResponses;

import com.localpayment.domain.entities.Card;
import com.localpayment.domain.entities.Person;

public class PersonResponse {

    private Person person;
    private String message;
    private String status;

    public PersonResponse() {

    }

    public PersonResponse(Person person, String message, String status) {
        this.person = person;
        this.message = message;
        this.status = status;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
