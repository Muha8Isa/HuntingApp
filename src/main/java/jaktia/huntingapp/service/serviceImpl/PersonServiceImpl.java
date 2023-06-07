package jaktia.huntingapp.service.serviceImpl;

import jaktia.huntingapp.dto.PersonDto;
import jaktia.huntingapp.entity.Person;
import jaktia.huntingapp.exceptions.DataDuplicateException;
import jaktia.huntingapp.exceptions.DataNotFoundException;
import jaktia.huntingapp.repository.PersonRepository;
import jaktia.huntingapp.service.PersonService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;
    @Autowired
    ModelMapper modelMapper; //used to convert to and from DTO. It has dependency inside pom file.
    @Override
    public List<PersonDto> getAll() {
        List<Person> personList = personRepository.findAllByOrderByIdDesc();
        return modelMapper.map(personList, new TypeToken<List<PersonDto>>() {
        }.getType());
    }

    @Override
    public PersonDto findById(Integer personId) {
        if(personId == null) throw new IllegalArgumentException("person id was null");
        Optional<Person> optionalPerson = personRepository.findById(personId);
        if(!optionalPerson.isPresent()) throw new DataNotFoundException("person id was not valid!");
        Person entity = optionalPerson.get();
        //  PersonDto dto = new PersonDto(entity.getId(), entity.getName());
        return modelMapper.map(entity, PersonDto.class); // Converts (from, to)
    }

    @Override
    public PersonDto create(PersonDto personDto) {
        if (personDto == null) throw new IllegalArgumentException("person data was null");
        if (personDto.getId() != 0) throw new IllegalArgumentException("person id should be null or zero");
        if (personRepository.findByEmailContainsIgnoreCase(personDto.getEmail()).isPresent())
            throw new DataDuplicateException("duplicate error");
        Person createdEntity = personRepository.save(modelMapper.map(personDto, Person.class));
        return modelMapper.map(createdEntity, PersonDto.class);
    }

    @Override
    public void update(PersonDto personDto) {
        if (personDto == null) throw new IllegalArgumentException("person data was null");
        if (personDto.getId() == 0) throw new IllegalArgumentException("person id should not be zero");

        if (!personRepository.findById(personDto.getId()).isPresent())
            throw new DataNotFoundException("person not found error");

        personRepository.save(modelMapper.map(personDto, Person.class));
        System.out.println("Person info updated");
    }

    @Override
    public void delete(Integer personId) {
        PersonDto personDto = findById(personId);
        if (personDto == null) throw new DataNotFoundException("id was not valid");
        personRepository.deleteById(personId);
    }
}
