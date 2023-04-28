package jaktia.huntingapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private String description;
    @ManyToMany
    @JoinTable(name = "team_person",
            joinColumns = @JoinColumn(name = "person_id"), ///////
            inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    private Set<Person> member;

    @OneToOne(cascade = CascadeType.ALL)
    private Person teamLeader;
    @OneToOne(cascade = CascadeType.ALL)
    private HuntingZone zone;
}
