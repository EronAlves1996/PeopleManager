package com.eronalves.peoplemanager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.eronalves.peoplemanager.model.Person;
import com.eronalves.peoplemanager.repositories.PersonRepository;
import com.eronalves.peoplemanager.service.PersonService;

@SpringBootTest
public class PersonServiceUnitTest {
    
    @MockBean
    PersonRepository repository;

    @Autowired
    PersonService service;

    @Test
    public void createPerson(){
        Person personTested = new Person();
        personTested.setId(1);
        personTested.setBirthDate(1996, Month.OCTOBER, 01);
        personTested.setName("Eron");

        when(repository.save(any())).thenReturn(personTested);

        Person person = new Person("Eron", LocalDate.of(1996, Month.OCTOBER, 01));
        Person personCreated = service.createPerson(person);
        assertNotNull(personCreated.getId());
        assertEquals(LocalDate.of(1996, Month.OCTOBER, 01), person.getBirthDate());
        assertNull(personCreated.getAdress());
    }


}
