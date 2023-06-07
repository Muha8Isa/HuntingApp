package jaktia.huntingapp.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserDto {
    @NotEmpty(message = "username should not empty")
    @Size(min = 2, max = 20, message = "username length should be between 2-40")
    private String username;
    @NotEmpty(message = "password should not empty")
    @Size(min = 6, max = 18, message = "password length should be between 6-18")
    private String password;

    private boolean active = true;
    private LocalDate regDate;
}
