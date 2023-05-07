package jaktia.huntingapp.repository;

import jaktia.huntingapp.entity.Dog;
import jaktia.huntingapp.entity.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class DogRepositoryTest {

    @Autowired
    DogRepository testObject;
    Dog addedDog1;
    Dog addedDog2;

    Person owner;
    @BeforeEach
    public void setup(){
        owner = new Person(1, "John", "Doe", "John.doe@jaktia.se", "070 - 1234 567", "Jönköping");
        Dog dog1 = new Dog(1, "dog1", "drever", 38, 15, true, owner);
        addedDog1 = testObject.save(dog1);
        assertNotNull(addedDog1);

        Dog dog2 = new Dog(2, "dog2", "drever", 55, 20, true, owner);
        addedDog2 = testObject.save(dog2);
        assertNotNull(addedDog2);
    }

    @Test
    public void test_findByName(){
        Optional<Dog> optionalDog = testObject.findByName(addedDog1.getName());
        assertTrue(optionalDog.isPresent());
        Dog actual = optionalDog.get();
        Dog expected = addedDog1;
        assertEquals(actual, expected);
    }
    @Test
    public void test_findByBreed(){
        List<Dog> dogList = testObject.findAllByBreed("drever");
        assertEquals(2, dogList.size());
        assertTrue(dogList.contains(addedDog1));
        assertTrue(dogList.contains(addedDog2));
    }
    @Test
    public void test_findByActive(){
        List<Dog> dogList = testObject.findAllByActive(true);
        assertEquals(2, dogList.size());
        assertTrue(dogList.contains(addedDog1));
        assertTrue(dogList.contains(addedDog2));
    }
    @Test
    public void test_findByOwner(){
        List<Dog> dogList = testObject.findAllByOwner(owner);
        assertEquals(2, dogList.size());
        assertTrue(dogList.contains(addedDog1));
        assertTrue(dogList.contains(addedDog2));
    }
}
