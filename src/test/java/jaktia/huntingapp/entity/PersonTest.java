package jaktia.huntingapp.entity;

import jaktia.huntingapp.Enum.Responsibility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {
    private Person person;
    private Contact contact1;
    private Contact contact2;

    @BeforeEach
    public void setup() {
        person = new Person(1, "John", "Doe", "johndoe@test.se", "070 - 1234 123", "Växjö");
        contact1 = new Contact(1, "Medic", null, "dsadsadas", Responsibility.MEDIC);
        contact2 = new Contact(1, "Vet", "07643", "dsadsadas", Responsibility.VET);
    }

    @Test
    public void test_addContact(){
        person.addContact(contact1);
        person.addContact(contact2);
        assertTrue(person.getContact().contains(contact1));
        assertTrue(person.getContact().contains(contact2));
    }
    @Test
    public void test_removeContact(){
        person.addContact(contact1);
        person.addContact(contact2);

        person.removeContact(contact1);
        person.removeContact(contact2);
        assertFalse(person.getContact().contains(contact1));
        assertFalse(person.getContact().contains(contact2));
    }


}
