package jaktia.huntingapp.dto;


import jaktia.huntingapp.Enum.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class AppUserDto {
    @NotEmpty(message = "username should not empty")
    @Size(min = 2, max = 20, message = "username length should be between 2-40")
    private String username;
    @NotEmpty(message = "password should not empty")
    @Size(min = 6, max = 18, message = "password length should be between 6-18")
    private String password;
    @NotEmpty(message = "Role should not be empty")
    private Role role;

    private boolean active;
    private LocalDate regDate;
    @Valid
    private PersonDto person;
}
