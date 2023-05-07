package jaktia.huntingapp.entity;

import jaktia.huntingapp.Enum.Role;
import jaktia.huntingapp.exceptions.NotValidPasswordException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.AccessDeniedException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AppUserTest {

    private AppUser appUser;
    private Person person;

    @BeforeEach
    public void setup(){
        person = new Person("John", "Doe", "johndoe@test.se", "070 - 1234 123", "Växjö");
        appUser = new AppUser("John", "password", Role.ROLE_ADMIN, true);
    }
        @Test
        public void test_addPerson () throws AccessDeniedException {
            appUser.addPerson(person, appUser.getRole());
            assertEquals(person, appUser.getPerson());
        }
    @Test
    public void test_addPersonAsUser () {
        appUser.setRole(Role.ROLE_USER);
        assertThrows(AccessDeniedException.class, () -> appUser.addPerson(person, appUser.getRole()));
    }

    @Test
    public void test_removePerson() throws AccessDeniedException {
        appUser.addPerson(person, appUser.getRole());
        appUser.removePerson(person, appUser.getRole());
        assertEquals(null, appUser.getPerson());
    }
    @Test
    public void test_passwordLength(){
        assertThrows(NotValidPasswordException.class, () -> appUser.setPassword("passw"));

    }
}
