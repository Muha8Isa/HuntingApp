package jaktia.huntingapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Dog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private String breed;
    @Column
    private float height;
    @Column
    private float weight;
    @Column
    private boolean active;
    @ManyToOne(cascade = CascadeType.ALL)
    private Person owner;
}
