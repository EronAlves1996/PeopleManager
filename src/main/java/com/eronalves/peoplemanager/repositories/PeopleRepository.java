package com.eronalves.peoplemanager.repositories;

import org.springframework.data.repository.CrudRepository;

import com.eronalves.peoplemanager.model.People;

public interface PeopleRepository extends CrudRepository<People, Integer> {
    
}
