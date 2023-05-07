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
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String breed;
    @Column(name = "height_cm")
    private float height;
    @Column(name = "weight_kg")
    private float weight;
    private boolean active;
    @ManyToOne(cascade = CascadeType.ALL)
    private Person owner;
}
