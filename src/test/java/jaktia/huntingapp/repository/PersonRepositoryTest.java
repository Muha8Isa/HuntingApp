package jaktia.huntingapp.repository;

import jaktia.huntingapp.entity.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class PersonRepositoryTest {

    @Autowired
    PersonRepository testObject;

    Person addedPerson1;
    Person addedPerson2;
    Person addedPerson3;

    @BeforeEach
    public void setup(){
        Person person1 = new Person(1, "first", "person", "first.person@hotmail.com", "070 - 123 4567", "Växjö");
        Person person2 = new Person(2, "first", "person", "second.person@hotmail.com", "070 - 123 4568", "Växjö");
        Person person3 = new Person(3, "third", "person", "third.person@hotmail.com", "070 - 123 4569", "Växjö");

        addedPerson1 = testObject.save(person1);
        addedPerson2 = testObject.save(person2);
        addedPerson3 = testObject.save(person3);

        assertNotNull(addedPerson1);
        assertNotNull(addedPerson2);
        assertNotNull(addedPerson3);
    }

    @Test
    public void test_findByEmail(){
        Optional<Person> optionalPerson = testObject.findByEmailContainsIgnoreCase("FiRsT.person@hotmail.com");
        assertTrue(optionalPerson.isPresent());
        Person actual = optionalPerson.get();
        Person expected = addedPerson1;
        assertEquals(actual, expected);
    }
    @Test
    public void test_findByFirstName(){
        List<Person> personList = testObject.findAllByFirstNameContainsIgnoreCase("FiRsT");
        assertEquals(2, personList.size());
        assertTrue(personList.contains(addedPerson1));
        assertTrue(personList.contains(addedPerson2));
    }
    @Test
    public void test_findByLastName(){
        List<Person> personList = testObject.findAllByLastNameContainsIgnoreCase("PeRsOn");
        assertEquals(3, personList.size());
        assertTrue(personList.contains(addedPerson1));
        assertTrue(personList.contains(addedPerson2));
        assertTrue(personList.contains(addedPerson3));
    }
}
