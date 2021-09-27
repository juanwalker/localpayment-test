package com.localpayment.domain.repositories;

import com.localpayment.domain.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer> {

    @Query("select u from User u where u.name= ?1")
    User findByName(String name);
}
