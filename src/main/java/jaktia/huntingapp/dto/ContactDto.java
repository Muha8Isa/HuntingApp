package jaktia.huntingapp.dto;

import jaktia.huntingapp.Enum.Responsibility;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
public class ContactDto {
    private int id;
    @NotEmpty(message = "Contact Name should not be empty")
    private String name;
    @Pattern(regexp = "\\d{10}", message = "Phone number must have exactly 10 digits")
    private String phoneNumber;
    @Pattern(regexp = "^\\S+@\\S+\\.\\S+$", message = "Invalid email format")
    private String email;
    @NotEmpty(message = "Role should not be empty")
    private Responsibility responsibility;
    private Set<@Valid PersonDto> caller = new HashSet<>();
}
