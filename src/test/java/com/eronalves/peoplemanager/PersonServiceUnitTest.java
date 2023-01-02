package com.eronalves.peoplemanager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

    @Test
    public void updatePersonById() throws Exception{
        Person personTested = new Person("Eron Alves", LocalDate.of(1996, Month.OCTOBER, 01));
        personTested.setId(1);

        when(repository.save(any())).thenReturn(personTested);

        Person person = new Person("Eron", LocalDate.of(1996, Month.OCTOBER, 01));
        person.setId(1);
        person.setName("Eron Alves");

        Person personUpdated = service.updatePersonById(person.getId(), person);
        assertNotEquals("Eron",personUpdated.getName());
    }

    @Test
    public void updatePersonById_whenIdIsNotOnObject_thenUpdateSuccesfull() throws Exception{
        Person personTested = new Person("Eron Alves", LocalDate.of(1996, Month.OCTOBER, 01));
        personTested.setId(1);

        Person person = new Person("Eron", LocalDate.of(1996, Month.OCTOBER, 01));
        person.setName("Eron Alves");

        when(repository.save(personTested)).thenReturn(personTested);


        Person personUpdated = service.updatePersonById(1, person);
        assertNotNull(personUpdated.getId());
        assertNotEquals("Eron",personUpdated.getName());
    }

    @Test
    public void updatePersonById_whenIdOnObjectIsDifferentFromIdPassedAsArgument_thenThrowError(){
        Person personTested = new Person("Eron Alves", LocalDate.of(1996, Month.OCTOBER, 01));
        personTested.setId(1);
        assertThrows(Exception.class, ()->{
            service.updatePersonById(2, personTested);
        });
    }


}
