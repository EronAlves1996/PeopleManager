package com.eronalves.peoplemanager.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public ResponseEntity<Object> createPerson(@RequestBody PersonDTO personDTO) {
        Person personCreated = null;
        try {
            personCreated = service.createPerson(DTOMapper.dtoToPerson(personDTO));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(new Object() {
                public String msg = ex.getMessage();
            });
        }
        BodyBuilder response = ResponseEntity.created(URI.create("/person/" + personCreated.getId()));
        return response.body(personCreated);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable("/id") int id, @RequestBody PersonDTO personDTO) {
        try {
            Person personUpdated = service.updatePersonById(id, DTOMapper.dtoToPerson(personDTO));
            return ResponseEntity.ok(personUpdated);
        } catch (Exception ex) {
            return ResponseEntity.notFound().build();
        }
    }

}
