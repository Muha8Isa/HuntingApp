package jaktia.huntingapp.dto;


import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
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
    @NotEmpty
    @Pattern(regexp = "\\d{10}", message = "Phone number must have exactly 10 digits")
    private String phoneNumber;
    private Set<@Valid ContactDto> contact = new HashSet<>();
    @NotEmpty
    private String address;

    @NotNull
    @Valid // @Valid takes the annotations in AppUserDto (NotEmpty, size....etc) into consideration.
    private AppUserDto appUser;


}
