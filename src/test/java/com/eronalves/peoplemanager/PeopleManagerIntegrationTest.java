package com.eronalves.peoplemanager;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.eronalves.peoplemanager.dto.DTOMapper;
import com.eronalves.peoplemanager.dto.PersonDTO;
import com.eronalves.peoplemanager.model.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@SpringBootTest
@AutoConfigureMockMvc
public class PeopleManagerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    static ObjectMapper om = new ObjectMapper();

    @BeforeAll
    public static void registerLocalDateModule() {
        om.registerModule(new JavaTimeModule());
    }

    @Test
    public void testCreatePerson() throws JsonProcessingException, Exception {
        PersonDTO personDTO = new PersonDTO("Eron Alves", "01/10/1996");
        Person person = DTOMapper.dtoToPerson(personDTO);
        person.setId(1);

        System.out.println(om.writeValueAsString(person.getBirthDate()));
        mockMvc.perform(post("/person/create").contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(personDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("id").value("1"))
                .andExpect(jsonPath("name").value(person.getName()))
                .andExpect(jsonPath("birthDate").value(person.getBirthDate().toString()))
                .andExpect(jsonPath("adresses").value(Matchers.empty()));
    }

}
