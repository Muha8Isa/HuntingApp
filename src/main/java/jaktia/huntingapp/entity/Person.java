package jaktia.huntingapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private int id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(unique = true, nullable = false)
    private String phoneNumber;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Contact> contact = new HashSet<>();
    @Column(nullable = false)
    private String address;

    @OneToOne
    @JoinColumn(name = "username", unique = true)
    private AppUser appUser;

    public Person(int id, String firstName, String lastName, String email, String phoneNumber, String address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public Person(String firstName, String lastName, String email, String phoneNumber, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    // add helper method for contact
    // add & remove
    public void addContact(Contact contact) {
        this.contact.add(contact);
    }
    public void removeContact(Contact contact) {
        this.contact.remove(contact);
    }

}
