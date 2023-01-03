package com.eronalves.peoplemanager.service;

import java.util.Optional;

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
        return persistOnDatabase(person);
    }

    @Override
    public Person updatePersonById(int id, Person person) throws Exception {
        if(person.getId() == null) person.setId(id);
        if(person.getId() != id) throw new Exception("Inconsistent Id information");
        return persistOnDatabase(person);
    }

    public Person persistOnDatabase(Person person){
        return repository.save(person);
    }

    @Override
    public Person getPersonById(int id) throws Exception {
        Optional<Person> personOpt = repository.findById(id);
        if(personOpt.isPresent()) return personOpt.get();
        throw new Exception("Person not finded");
    }

}
