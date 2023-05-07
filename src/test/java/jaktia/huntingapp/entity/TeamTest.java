package jaktia.huntingapp.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TeamTest {
    private Team team;
    private Person person1;
    private Person person2;

    @BeforeEach
    public void setup() {
        person1 = new Person("John", "Doe", "John.doe@test.se", "070 - 123 1234", "Växjö");
        person2 = new Person("Jane", "Doe", "Jane.doe@test.se", "070 - 123 1235", "Växjö");


        team = new Team(1, "Team1", "Best team here");

    }

    @Test
    public void test_addMember() {
        team.addMember(person1);
        assertEquals(1, team.getMembers().size());
        assertTrue(team.getMembers().contains(person1));

        team.addMember(person2);
        assertEquals(2, team.getMembers().size());
        assertTrue(team.getMembers().contains(person2));

    }

    @Test
    public void test_removeMember() {
        team.addMember(person1);
        team.addMember(person2);

        team.removeMember(person1);
        assertEquals(1, team.getMembers().size());
        assertFalse(team.getMembers().contains(person1));

        team.removeMember(person2);
        assertEquals(0, team.getMembers().size());
        assertFalse(team.getMembers().contains(person2));
    }
}
