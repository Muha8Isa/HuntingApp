package jaktia.huntingapp.repository;

import jaktia.huntingapp.Enum.Responsibility;
import jaktia.huntingapp.entity.Contact;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ContactRepositoryTest {

    @Autowired
    ContactRepository testObject;
    Contact createdContact1;
    Contact createdContact2;
    Contact createdContact3;

    @BeforeEach
    public void setup(){
        Contact contactData1 = new Contact(1, "John", "070 - 1111 111", "John.medic@jaktia.se", Responsibility.MEDIC);
        Contact contactData2 = new Contact(2, "Jack", "070 - 2222 222", "Jack.medic@jaktia.se", Responsibility.MEDIC);
        Contact contactData3 = new Contact(3, "Jason", "070 - 3333 333", "Jason.medic@jaktia.se", Responsibility.MEDIC);
        createdContact1 = testObject.save(contactData1);
        createdContact2 = testObject.save(contactData2);
        createdContact3 = testObject.save(contactData3);
        assertNotNull(createdContact1);
        assertNotNull(createdContact2);
        assertNotNull(createdContact3);
    }

    @Test
    public void test_findByName(){
        Optional<Contact> contactOptional = testObject.findByName(createdContact1.getName());
        assertTrue(contactOptional.isPresent());
        Contact actualData = contactOptional.get();
        Contact expectedData = createdContact1;
        assertEquals(actualData, expectedData);
    }
    @Test
    public void test_findByResponsibility(){
        List<Contact> contactList = testObject.findAllByResponsibility(Responsibility.MEDIC);
        assertEquals(3, contactList.size());
        assertTrue(contactList.contains(createdContact1));
        assertTrue(contactList.contains(createdContact2));
        assertTrue(contactList.contains(createdContact3));
    }

    @Test
    public void test_findByEmail(){
        Optional<Contact> contactOptional = testObject.findByEmailIgnoreCase(createdContact1.getEmail());
        assertTrue(contactOptional.isPresent());
        Contact actualData = contactOptional.get();
        Contact expectedData = createdContact1;
        assertEquals(actualData, expectedData);
    }
}
