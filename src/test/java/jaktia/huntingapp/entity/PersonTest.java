package jaktia.huntingapp.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {
    private Person person;

    @BeforeEach
    public void setup() {
        person = new Person(1, "John", "Doe", "johndoe@test.se", "070 - 1234 123", "Växjö");
    }

    @Test
    public void test_addTask() {
        // test add task
        Task task = new Task();
        task.setId(1);
        task.setTitle("Task 1");
        person.addTask(task);
        assertEquals(1, person.getTaskList().size());
        assertEquals(person, task.getAssignee());
    }

    @Test
    public void test_removeTask() {
        // test remove task.
        Task task = new Task();
        task.setId(1);
        task.setTitle("Task 1");
        person.addTask(task);
        person.removeTask(task);
        assertEquals(0, person.getTaskList().size());
        assertNull(task.getAssignee());
    }

    @Test
    public void test_addDog() {
        // Test add dog to a respective owner.
        Dog dog = new Dog();
        dog.setId(1);
        dog.setName("Dog");
        person.addDog(dog);
        assertEquals(1, person.getDogs().size());
        assertEquals(person, dog.getOwner());
    }

    @Test
    public void test_removeDog() {
        // Add two dogs to the person
        Dog dog1 = new Dog();
        dog1.setId(1);
        dog1.setName("Dog1");
        person.addDog(dog1);

        Dog dog2 = new Dog();
        dog2.setId(2);
        dog2.setName("Dog2");
        person.addDog(dog2);

        // Remove dog1
        person.removeDog(dog1);
        assertEquals(1, person.getDogs().size());
        assertNull(dog1.getOwner());

        // Remove dog2
        person.removeDog(dog2);
        assertEquals(0, person.getDogs().size());
        assertNull(dog2.getOwner());
    }

}
