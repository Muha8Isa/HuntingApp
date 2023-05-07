package jaktia.huntingapp.repository;

import jaktia.huntingapp.Enum.Role;
import jaktia.huntingapp.entity.AppUser;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@DataJpaTest
public class AppUserRepositoryTest {

    @Autowired
    AppUserRepository testObject;
    AppUser createdAppUser1;
    AppUser createdAppUser2;
    String newPassword = "newPassword123";
    LocalDate from = LocalDate.of(2022, 1, 1);
    LocalDate to = LocalDate.of(2022, 12, 31);

    @BeforeEach
    public void setup(){
        AppUser appUserData1 = new AppUser("jack12", "987oiu", Role.ROLE_ADMIN, true);
        appUserData1.setRegDate(LocalDate.of(2022, 3, 15));
        createdAppUser1 = testObject.save(appUserData1);
        assertNotNull(createdAppUser1);

        AppUser appUserData2 = new AppUser("john123", "987iou", Role.ROLE_USER, true);
        appUserData2.setRegDate(LocalDate.of(2022, 7, 12));
        createdAppUser2 = testObject.save(appUserData2);
        assertNotNull(createdAppUser2);


        createdAppUser1.setPassword(newPassword);
        testObject.resetPassword(createdAppUser1.getUsername(), newPassword);
    }

    @Test
    public void test_selectByUsername(){
        Optional<AppUser> appUserOptional = testObject.selectByUsername(createdAppUser1.getUsername());
        assertTrue(appUserOptional.isPresent());
        AppUser actualData = appUserOptional.get();
        AppUser expectedData = createdAppUser1;
        assertEquals(expectedData, actualData);
    }
    @Test
    public void test_resetPassword() {
        Optional<AppUser> updatedAppUserOptional = testObject.selectByUsername(createdAppUser1.getUsername());
        assertTrue(updatedAppUserOptional.isPresent());
        AppUser updatedAppUser = updatedAppUserOptional.get();
        assertEquals(newPassword, updatedAppUser.getPassword());
    }
    @Test
    public void test_findByRole(){
        Optional<AppUser> appUserOptional = testObject.findByRole(createdAppUser1.getRole());
        assertTrue(appUserOptional.isPresent());
        AppUser actualData = appUserOptional.get();
        AppUser expectedData = createdAppUser1;
        assertEquals(expectedData, actualData);
    }
    @Test
    public void test_selectByRegistrationDate(){
        List<AppUser> appUsers = testObject.selectByRegistrationDate(from, to);
        assertNotNull(appUsers);
        assertEquals(2, appUsers.size());
        assertTrue(appUsers.contains(createdAppUser1));
        assertTrue(appUsers.contains(createdAppUser2));
    }

}
