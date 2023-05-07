package jaktia.huntingapp.repository;

import jaktia.huntingapp.entity.Dog;
import jaktia.huntingapp.entity.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DogRepository extends CrudRepository<Dog, Integer> {
    Optional<Dog> findByName(String name);
    List<Dog> findAllByBreed(String breed);
    List<Dog> findAllByActive(boolean active);

    List<Dog> findAllByOwner(Person owner);
}
