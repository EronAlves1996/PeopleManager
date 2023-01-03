package com.eronalves.peoplemanager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.eronalves.peoplemanager.model.Address;
import com.eronalves.peoplemanager.model.Person;
import com.eronalves.peoplemanager.repositories.PersonRepository;
import com.eronalves.peoplemanager.service.PersonService;

@SpringBootTest
public class PersonServiceUnitTest {

	@MockBean
	PersonRepository repository;
    
	@Autowired
	PersonService service;
	private Person personTested;
	private Person person;
    private Address address;
    
	@BeforeEach
	public void initializePersons() {
        personTested = new Person("Eron", LocalDate.of(1996, Month.OCTOBER, 01));
		personTested.setId(1);
        person = new Person("Eron", LocalDate.of(1996, Month.OCTOBER, 01));
	}
    
    @BeforeEach
    public void initializeAddress(){
        address = new Address("Rua dos bobos", "10000-000", "0", "Esmero", true);
    }
    
    @BeforeEach
    public void makeMocks(){
        when(repository.save(any())).thenReturn(personTested);
        when(repository.findById(1)).thenReturn(Optional.of(personTested));
        when(repository.findAll()).thenReturn(Arrays.asList(personTested));
    }

	@Test
	public void createPerson() {
		Person personCreated = service.createPerson(person);

		assertNotNull(personCreated.getId());
		assertEquals(LocalDate.of(1996, Month.OCTOBER, 01), person.getBirthDate());
		assertEquals(0, personCreated.getAdresses().size());
	}

	@Test
	public void updatePersonById() throws Exception {
		personTested.setName("Eron Alves");

		person.setId(1);
		person.setName("Eron Alves");

		Person personUpdated = service.updatePersonById(person.getId(), person);
		assertNotEquals("Eron", personUpdated.getName());
	}

	@Test
	public void updatePersonById_whenIdIsNotOnObject_thenUpdateSuccesfull() throws Exception {
		personTested.setName("Eron Alves");

		person.setName("Eron Alves");

		Person personUpdated = service.updatePersonById(1, person);
		assertNotNull(personUpdated.getId());
		assertNotEquals("Eron", personUpdated.getName());
	}

	@Test
	public void updatePersonById_whenIdOnObjectIsDifferentFromIdPassedAsArgument_thenThrowError() {
		personTested.setName("Eron Alves");

		assertThrows(Exception.class, () -> {
			service.updatePersonById(2, personTested);
		});
	}

    @Test
    public void getPersonById() throws Exception{
        Person person = service.getPersonById(1);
        assertNotNull(person);
    }

    @Test
    public void getPersonById_whenItNotExists() throws Exception{
        assertThrows(Exception.class, ()->service.getPersonById(2));
    }

    @Test
    public void getAllPersons(){
        assertEquals(Arrays.asList(personTested), service.getAllPersons());
    }

    @Test
    public void createAddressForPersonById() throws Exception{
        Person person = service.createAddressForPersonById(1, address);
        List<Address> addressCreated = person.getAdresses();
        assertNotNull(addressCreated);
        assertEquals(1, addressCreated.size());
        assertEquals("Rua dos bobos", addressCreated.get(0).getStreetName());
    }

    @Test
    public void createAddressForPersonById_whenItNotExists_thenThrowException() {
        assertThrows(Exception.class, ()->service.createAddressForPersonById(2, address));        
    }

}
