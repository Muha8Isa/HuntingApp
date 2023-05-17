package jaktia.huntingapp.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
public class PersonDto {
    private int id;
    @NotEmpty(message = "FirstName should not empty")
    @Size(min = 2, max = 40, message = "FirstName length should be between 2-40")
    private String firstName;
    @NotEmpty(message = "LastName should not be empty")
    @Size(min = 2, max = 40, message = "LastName length should be between 2-40")
    private String lastName;
    @NotEmpty(message = "email is required")
    @Pattern(regexp = "^\\S+@\\S+\\.\\S+$", message = "Invalid email format")
    private String email;
    @Pattern(regexp = "\\d{10}", message = "Phone number must have exactly 10 digits")
    private String phoneNumber;
    private Set<@Valid ContactDto> contact = new HashSet<>();
    private String address;
    private List<@Valid TaskDto> taskList = new ArrayList<>();
    private Set<@Valid TeamDto> team;
    private Set<@Valid DogDto> dogs = new HashSet<>();


}
