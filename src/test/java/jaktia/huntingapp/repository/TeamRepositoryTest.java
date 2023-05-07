package jaktia.huntingapp.repository;

import jaktia.huntingapp.entity.HuntingZone;
import jaktia.huntingapp.entity.Person;
import jaktia.huntingapp.entity.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class TeamRepositoryTest {
    @Autowired
    TeamRepository testObject;

    Team createdTeam;
    Person leader;

    @BeforeEach
    public void setup(){
        leader = new Person(1, "first", "person", "first.person@hotmail.com", "070 - 123 4567", "Växjö");
        HuntingZone zone1 = new HuntingZone(1, "Forest", "So many trees, vision is limited", "Jönköping", "Forest");
        Team team = new Team(1, "Hawks", "Best team in the region", leader, zone1);

        createdTeam = testObject.save(team);

        assertNotNull(createdTeam);
    }

    @Test
    public void test_findByName(){
        Optional<Team> teamOptional = testObject.findByNameContainsIgnoreCase("hawK");
        assertTrue(teamOptional.isPresent());
        Team actual = teamOptional.get();
        Team expected = createdTeam;
        assertEquals(actual, expected);
    }
    @Test
    public void test_findByLeader(){
        Optional<Team> teamOptional = testObject.findByTeamLeader(leader);
        assertTrue(teamOptional.isPresent());
        Team actual = teamOptional.get();
        Team expected = createdTeam;
        assertEquals(actual, expected);
    }
}
