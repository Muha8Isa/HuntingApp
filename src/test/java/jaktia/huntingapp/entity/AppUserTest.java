package jaktia.huntingapp.entity;

import jaktia.huntingapp.Enum.Role;
import jaktia.huntingapp.exceptions.NotValidPasswordException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class AppUserTest {

    private AppUser appUser;

    @BeforeEach
    public void setup(){
        appUser = new AppUser("John", "password", Role.ROLE_ADMIN, true);
    }
    @Test
    public void test_passwordLength(){
        assertThrows(NotValidPasswordException.class, () -> appUser.setPassword("passw"));

    }
}
