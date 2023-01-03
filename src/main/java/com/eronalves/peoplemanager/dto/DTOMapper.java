package com.eronalves.peoplemanager.dto;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.eronalves.peoplemanager.model.Person;

public class DTOMapper {

    public static Person dtoToPerson(PersonDTO dto) {
        List<Integer> birthDateSplitted = Stream
                .of(dto.getBirthDate().split("/"))
                .map(datePart -> Integer.parseInt(datePart))
                .collect(Collectors.toList());

        Integer year = birthDateSplitted.get(2);
        Integer month = birthDateSplitted.get(1);
        Integer day = birthDateSplitted.get(0);

        Person person = new Person(dto.getName(), LocalDate.of(year, Month.of(month), day));
        return person;
    }

}
