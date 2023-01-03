package com.eronalves.peoplemanager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.eronalves.peoplemanager.dto.DTOMapper;
import com.eronalves.peoplemanager.dto.PersonDTO;
import com.eronalves.peoplemanager.model.Person;

@SpringBootTest
public class DtoMapperUnitTest {

    @Test
    public void convertPersonDTOToPerson() {
        PersonDTO personDTO = new PersonDTO("Eron Alves", "01/10/1996");
        Person person = DTOMapper.dtoToPerson(personDTO);
        LocalDate birthDate = person.getBirthDate();
        assertNotNull(birthDate);
        assertEquals(1, birthDate.getDayOfMonth());
        assertEquals(10, birthDate.getMonthValue());
        assertEquals(1996, birthDate.getYear());
    }
}
