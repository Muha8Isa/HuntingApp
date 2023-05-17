package jaktia.huntingapp.entity;

import jaktia.huntingapp.Enum.Responsibility;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private String phoneNumber;
    @Column(unique = true, nullable = false)
    private String email;

    private Responsibility responsibility;
    @ManyToMany
    @JoinTable(name = "contact_person",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "contact_id")
    )
    private Set<Person> caller = new HashSet<>();

    public Contact(int id, String name, String phoneNumber, String email, Responsibility responsibility) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.responsibility = responsibility;
    }
}
