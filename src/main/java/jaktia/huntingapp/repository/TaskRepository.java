package jaktia.huntingapp.repository;

import jaktia.huntingapp.entity.Person;
import jaktia.huntingapp.entity.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface TaskRepository extends CrudRepository<Task, Integer> {
    Optional<Task> findByTitleContainsIgnoreCase(String title);
    List<Task> findAllByAssignee(Person assignee);
    List<Task> findAllByDone(boolean done);
}
