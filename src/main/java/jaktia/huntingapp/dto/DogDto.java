package jaktia.huntingapp.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Getter
@Setter
@ToString
public class DogDto {
    private int id;
    @NotEmpty(message = "Name is required")
    private String name;
    @NotEmpty(message = "Breed is required")
    private String breed;
    @Positive(message = "Height must be a positive number")
    private float height;
    @Positive(message = "Weight must be a positive number")
    private float weight;
    private boolean active;
    @Valid
    private PersonDto owner;
}
