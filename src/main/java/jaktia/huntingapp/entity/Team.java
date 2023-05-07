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
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
    private String description;
    @ManyToMany
    @JoinTable(name = "team_person",
            joinColumns = @JoinColumn(name = "person_id"), ///////
            inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    private Set<Person> members = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    private Person teamLeader;
    @OneToOne(cascade = CascadeType.ALL)
    private HuntingZone zone;

    public Team(int id, String name, String description, Person teamLeader, HuntingZone zone) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.teamLeader = teamLeader;
        this.zone = zone;
    }

    public Team(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public void addMember(Person person) {
        if (members == null) {
            members = new HashSet<>();
        }
        members.add(person);
    }
    public void removeMember(Person person) {
        if (members != null) {
            members.remove(person);
        }
    }


}
