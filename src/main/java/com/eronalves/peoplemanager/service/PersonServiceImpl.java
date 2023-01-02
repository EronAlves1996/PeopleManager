package com.eronalves.peoplemanager.service;

import org.springframework.stereotype.Service;

import com.eronalves.peoplemanager.model.Person;
import com.eronalves.peoplemanager.repositories.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService {

    private PersonRepository repository;

    public PersonServiceImpl(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public Person createPerson(Person person) {
        return repository.save(person);
    }

}
