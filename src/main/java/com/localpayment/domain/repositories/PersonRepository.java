package com.localpayment.domain.repositories;

import com.localpayment.domain.entities.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person,Integer> {



}