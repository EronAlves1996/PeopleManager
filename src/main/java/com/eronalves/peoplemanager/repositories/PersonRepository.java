package com.eronalves.peoplemanager.repositories;

import org.springframework.data.repository.CrudRepository;

import com.eronalves.peoplemanager.model.Person;

public interface PersonRepository extends CrudRepository<Person, Integer> {
    
}
