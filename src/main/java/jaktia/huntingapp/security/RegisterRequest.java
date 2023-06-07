package jaktia.huntingapp.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest{
    private String username;
    @Size(min = 6, max = 18, message = "password length should be between 6-18")
    private String password;
}
