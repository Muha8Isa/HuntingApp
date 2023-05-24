package jaktia.huntingapp.repository;

import jaktia.huntingapp.entity.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {
    Optional<Person> findByEmailContainsIgnoreCase(String email);

    List<Person> findAllByFirstNameContainsIgnoreCase(String firstName);
    List<Person> findAllByLastNameContainsIgnoreCase(String lastName);
    List<Person> findAllByOrderByIdDesc();


}
