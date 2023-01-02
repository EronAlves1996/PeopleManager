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

    @Override
    public Person updatePersonById(int id, Person person) throws Exception {
        if(person.getId() == null) person.setId(id);
        if(person.getId() != id) throw new Exception("Inconsistent Id information");
        return repository.save(person);
    }

}
