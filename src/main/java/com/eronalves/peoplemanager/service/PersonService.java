package com.eronalves.peoplemanager.service;

import java.util.Optional;

import com.eronalves.peoplemanager.model.Person;

public interface PersonService {

    Person createPerson(Person person);

    Person updatePersonById(int id, Person person) throws Exception;

    Person getPersonById(int id) throws Exception;
}
