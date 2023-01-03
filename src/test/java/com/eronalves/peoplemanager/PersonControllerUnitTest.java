package com.eronalves.peoplemanager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import com.eronalves.peoplemanager.controller.PersonController;
import com.eronalves.peoplemanager.dto.DTOMapper;
import com.eronalves.peoplemanager.dto.PersonDTO;
import com.eronalves.peoplemanager.model.Person;
import com.eronalves.peoplemanager.service.PersonService;

@SpringBootTest
public class PersonControllerUnitTest {

    @Autowired
    PersonController controller;

    @MockBean
    PersonService service;

    private PersonDTO personDTO;
    private Person person;

    @BeforeEach
    public void setDTO() {
        personDTO = new PersonDTO("Eron Alves", "01/10/1996");
    }

    @BeforeEach
    public void setPerson() {
        person = new Person("Eron Alves", LocalDate.of(1996, Month.OCTOBER, 01));
        person.setId(1);
    }

    @Test
    public void testCreatePerson() {
        when(service.createPerson(DTOMapper.dtoToPerson(personDTO))).thenReturn(person);
        
        ResponseEntity<Person> response = controller.createPerson(personDTO);
        assertEquals(HttpStatusCode.valueOf(201), response.getStatusCode());

        Person person = response.getBody();
        assertNotNull(person);
        assertNotNull(person.getId());
        assertEquals(0, person.getAdresses().size());
    }
}
