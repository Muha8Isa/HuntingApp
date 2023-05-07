package jaktia.huntingapp.repository;

import jaktia.huntingapp.entity.Person;
import jaktia.huntingapp.entity.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class TaskRepositoryTest {

    @Autowired
    TaskRepository testObject;
    Task createdTask1;
    Task createdTask2;
    Task createdTask3;

    Person assignee;

    @BeforeEach
    public void setup(){
        assignee = new Person(1, "first", "person", "first.person@hotmail.com", "070 - 123 4567", "Växjö");
        Task task1 = new Task(1,"Clean", "Clean the gun barrel", assignee, false);
        Task task2 = new Task(2,"Check", "Check the dogs", assignee, true);
        Task task3 = new Task(3,"buy", "buy cartridges", assignee, true);

        createdTask1 = testObject.save(task1);
        createdTask2 = testObject.save(task2);
        createdTask3 = testObject.save(task3);
        assertNotNull(createdTask1);
        assertNotNull(createdTask2);
        assertNotNull(createdTask3);
    }

    @Test
    public void test_findByTitle(){
        Optional<Task> taskOptional = testObject.findByTitleContainsIgnoreCase("buY");
        assertTrue(taskOptional.isPresent());
        Task actual = taskOptional.get();
        Task expected = createdTask3;
        assertEquals(actual, expected);
    }
    @Test
    public void test_findByAssignee(){
        List<Task> taskList = testObject.findAllByAssignee(assignee);
        assertEquals(3, taskList.size());
        assertTrue(taskList.contains(createdTask1));
        assertTrue(taskList.contains(createdTask2));
        assertTrue(taskList.contains(createdTask3));
    }
    @Test
    public void test_findByDone(){
        List<Task> taskList = testObject.findAllByDone(true);
        assertEquals(2, taskList.size());
        assertTrue(taskList.contains(createdTask2));
        assertTrue(taskList.contains(createdTask3));
    }
}
