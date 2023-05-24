package jaktia.huntingapp.service;

import jaktia.huntingapp.dto.PersonDto;

import java.util.List;

public interface PersonService {

    List<PersonDto> getAll();
    PersonDto findById(Integer personId);
    PersonDto create(PersonDto personDto);
    void update(PersonDto personDto);
    void delete(Integer personId);
}
