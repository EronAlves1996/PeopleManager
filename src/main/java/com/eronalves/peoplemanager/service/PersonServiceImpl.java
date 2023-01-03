package com.eronalves.peoplemanager.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.eronalves.peoplemanager.model.Address;
import com.eronalves.peoplemanager.model.Person;
import com.eronalves.peoplemanager.repositories.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService {

    private static final String PERSON_NOT_FINDED_MSG = "Person not finded";
    private static final String INCONSISTENT_ID_MSG = "Inconsistent Id information";
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
        if(person.getId() != id) throw new Exception(INCONSISTENT_ID_MSG);
        return persistOnDatabase(person);
    }

    
    @Override
    public Person getPersonById(int id) throws Exception {
        Optional<Person> personOpt = repository.findById(id);
        if(personOpt.isPresent()) return personOpt.get();
        throw new Exception(PERSON_NOT_FINDED_MSG);
    }
    
    @Override
    public List<Person> getAllPersons() {
        Iterable<Person> personIterable = repository.findAll();
        ArrayList<Person> personList = new ArrayList<Person>();
        personIterable.forEach(personList::add);
        return personList;
    }
    
    private Person persistOnDatabase(Person person){
        return repository.save(person);
    }

    @Override
    public Person createAddressForPersonById(int id, Address address) throws Exception {
        Person person = getPersonById(id);
        person.addAdress(address);
        return updatePersonById(id, person);
    }
}
