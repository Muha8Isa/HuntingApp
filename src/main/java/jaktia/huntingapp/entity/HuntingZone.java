package jaktia.huntingapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class HuntingZone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    @Column(unique = true)
    private String name;
    @Column
    private String description;
    @Column
    private String location;
    @Column(nullable = false)
    private int area;
    @Column(nullable = false)
    private String terrain;
    @Column
    private LocalDate season;
}
