package com.eronalves.peoplemanager.service;

import com.eronalves.peoplemanager.model.Person;

public interface PersonService {

    Person createPerson(Person person);

    Person updatePersonById(int id, Person person) throws Exception;
}
