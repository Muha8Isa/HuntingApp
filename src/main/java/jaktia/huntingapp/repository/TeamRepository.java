package jaktia.huntingapp.repository;

import jaktia.huntingapp.entity.Person;
import jaktia.huntingapp.entity.Team;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TeamRepository extends CrudRepository<Team, Integer> {

    Optional<Team> findByNameContainsIgnoreCase(String name);
    Optional<Team> findByTeamLeader(Person teamLeader);

}
