package com.example;

/**
 * Created by hillaryskye on 3/1/17.
 */

import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {

    Person findByFirstName(String firstName);
}