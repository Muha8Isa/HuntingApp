package jaktia.huntingapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
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
    @ManyToMany
    @JoinTable(name = "contact_person",
            joinColumns = @JoinColumn(name = "contact_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    private Set<Contact> contact = new HashSet<>();

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

    public void addTask(Task task) {
        this.taskList.add(task);
        task.setAssignee(this);
    }
    public void removeTask(Task task) {
        task.setAssignee(null);
        taskList.remove(task);
    }
    public void addDog(Dog dog) {
        if (dogs == null) {
            dogs = new HashSet<>();
        }
        dogs.add(dog);
        dog.setOwner(this);
    }
    public void removeDog(Dog dog) {
        dog.setOwner(null);
        dogs.remove(dog);
    }


}
