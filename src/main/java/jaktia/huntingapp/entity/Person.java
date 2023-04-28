package jaktia.huntingapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

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
    @Column(unique = true)
    private String phoneNumber;
    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private AppUser userId;
    @ManyToMany
    @JoinTable(name = "contact_person",
            joinColumns = @JoinColumn(name = "contact_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    private Set<Contact> contact = new HashSet<>();
    @Column
    private String address;
    @OneToMany (mappedBy = "assignee")
    private List<Task> taskList = new ArrayList<>();
    @ManyToMany
    @JoinTable(name = "team_person",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id") ///////////
    )
    private Set<Team> team;

    @OneToMany(mappedBy = "owner")
    private Set<Dog> dogs = new HashSet<>();
}
