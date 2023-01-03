package com.eronalves.peoplemanager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import com.eronalves.peoplemanager.controller.PersonController;
import com.eronalves.peoplemanager.dto.AddressDTO;
import com.eronalves.peoplemanager.dto.DTOMapper;
import com.eronalves.peoplemanager.dto.PersonDTO;
import com.eronalves.peoplemanager.model.Address;
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
        
        ResponseEntity<Object> response = controller.createPerson(personDTO);
        assertEquals(HttpStatusCode.valueOf(201), response.getStatusCode());

        Person person = (Person) response.getBody();
        assertNotNull(person);
        assertNotNull(person.getId());
        assertEquals(0, person.getAdresses().size());
    }

    @Test
    public void testCreatePersonWithInvalidDate() {
        PersonDTO personDTO2 = new PersonDTO("Eron Alves", "10 10 1995");
        ResponseEntity<Object> response = controller.createPerson(personDTO2);
        assertEquals(HttpStatusCode.valueOf(400), response.getStatusCode());
    }

    @Test
    public void testUpdatePerson() throws Exception {
        personDTO.setBirthDate("01/02/1994");
        person.setBirthDate(LocalDate.of(1994, Month.FEBRUARY, 01));
        when(service.updatePersonById(1, DTOMapper.dtoToPerson(personDTO))).thenReturn(person);

        ResponseEntity<Person> response = controller.updatePerson(1, personDTO);
        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());

        Person personUpdated = response.getBody();
        assertNotNull(personUpdated);
    }

    @Test
    public void testGetPerson() throws Exception{
        when(service.getPersonById(1)).thenReturn(person);
        ResponseEntity<Person> response = controller.getPerson(1);
        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        Person person = response.getBody();
        assertNotNull(person);
        assertEquals(1, person.getId());
    }

    @Test
    public void testGetAllPersons() {
        when(service.getAllPersons()).thenReturn(Arrays.asList(person));

        ResponseEntity<List<Person>> response = controller.getAllPersons();
        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());

        List<Person> persons = response.getBody();
        assertNotNull(persons);
        assertNotEquals(0, persons.size());
    }

    @Test
    public void testAddAddressByPersonId() throws Exception {
        AddressDTO addressDTO = new AddressDTO("Rua dos bobos", "00000-000", "0", "Lugar Nenhum", true);
        person.addAdress(DTOMapper.dtoToAddress(addressDTO));

        when(service.createAddressForPersonById(1, DTOMapper.dtoToAddress(addressDTO))).thenReturn(person);

        ResponseEntity<List<Address>> response = controller.addAddressForPerson(1, addressDTO);
        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());

        List<Address> addressList = response.getBody();

        assertNotNull(addressList);
        assertEquals(1, addressList.size());
    }

    @Test
    public void testGetAllAdressesFromPersonId() throws Exception {
        testAddAddressByPersonId();
        ResponseEntity<List<Address>> response = controller.getAllAddressFromPerson(1);
    }

}
