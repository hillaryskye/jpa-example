package com.example;

/**
 * Created by hillaryskye on 3/1/17.
 */

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/people")
public class PersonController {

    private final PersonRepository repository;

    public PersonController(PersonRepository repository) {

        this.repository = repository;
    }

    @PostMapping("")
    public Person create(@RequestBody Person person) {
        this.repository.save(person);
        return person;
    }

    @GetMapping("")
    public Iterable<Person> list() {
        return this.repository.findAll();
    }

}

