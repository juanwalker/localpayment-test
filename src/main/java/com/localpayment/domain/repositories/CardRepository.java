package com.localpayment.domain.repositories;

import com.localpayment.domain.entities.Card;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CardRepository extends CrudRepository<Card,Integer> {

    @Query("select c from Card c where c.person.id = ?1")
     List<Card> findByPersonId(Integer id);
}