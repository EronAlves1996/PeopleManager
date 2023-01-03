package com.eronalves.peoplemanager.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eronalves.peoplemanager.dto.DTOMapper;
import com.eronalves.peoplemanager.dto.PersonDTO;
import com.eronalves.peoplemanager.model.Person;
import com.eronalves.peoplemanager.service.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {

    private PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<Person> createPerson(@RequestBody PersonDTO personDTO) {
        Person personCreated = service.createPerson(DTOMapper.dtoToPerson(personDTO));
        BodyBuilder response = ResponseEntity.created(URI.create("/person/" + personCreated.getId()));
        return response.body(personCreated);
    }

}
