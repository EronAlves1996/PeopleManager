package com.eronalves.peoplemanager;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.web.servlet.MockMvc;

import com.eronalves.peoplemanager.dto.AddressDTO;
import com.eronalves.peoplemanager.dto.DTOMapper;
import com.eronalves.peoplemanager.dto.PersonDTO;
import com.eronalves.peoplemanager.model.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
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

        mockMvc.perform(post("/person/create").contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(personDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("id").value("1"))
                .andExpect(jsonPath("name").value(person.getName()))
                .andExpect(jsonPath("birthDate").value(person.getBirthDate().toString()))
                .andExpect(jsonPath("adresses").value(Matchers.empty()));
    }

    @Test
    public void testUpdatePerson() throws JsonProcessingException, Exception {
        PersonDTO personDTO = new PersonDTO("Eron Alves", "01/10/1996");
        PersonDTO personDTO2 = new PersonDTO("Eron Alves", "02/10/1996");

        mockMvc.perform(post("/person/create").contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(personDTO)));

        mockMvc.perform(put("/person/update/1").contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(personDTO2)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value("1"))
                .andExpect(jsonPath("birthDate").value("1996-10-02"));
    }

    @Test
    public void testReadPerson() throws JsonProcessingException, Exception {
        PersonDTO personDTO = new PersonDTO("Eron Alves", "01/10/1996");
        mockMvc.perform(post("/person/create").contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(personDTO)));

        mockMvc.perform(get("/person/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value("1"))
                .andExpect(jsonPath("name").value(personDTO.getName()))
                .andExpect(jsonPath("birthDate").value("1996-10-01"))
                .andExpect(jsonPath("adresses").value(Matchers.empty()));

    }

    @Test
    public void testReadAllPersons() throws JsonProcessingException, Exception {
        PersonDTO personDTO = new PersonDTO("Eron Alves", "01/10/1996");
        PersonDTO personDTO2 = new PersonDTO("João da Silva", "02/10/1998");

        mockMvc.perform(post("/person/create").contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(personDTO)));
        mockMvc.perform(post("/person/create").contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(personDTO2)));

        mockMvc.perform(get("/person"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));
    }

    @Test
    public void testCreateAddress() throws JsonProcessingException, Exception {
        PersonDTO personDTO = new PersonDTO("Eron Alves", "01/10/1996");
        mockMvc.perform(post("/person/create").contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(personDTO)));

        AddressDTO addressDTO = new AddressDTO("Rua dos Bobos", "10000-000", "0", "Lugar Nenhum", true);
        mockMvc.perform(post("/person/address/add/1").contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(addressDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].streetName").value(addressDTO.getStreetName()));
    }

}
