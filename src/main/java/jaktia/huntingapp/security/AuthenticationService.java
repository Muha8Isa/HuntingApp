package jaktia.huntingapp.security;

import jaktia.huntingapp.Enum.Role;
import jaktia.huntingapp.entity.AppUser;
import jaktia.huntingapp.exceptions.DataDuplicateException;
import jaktia.huntingapp.exceptions.DataNotFoundException;
import jaktia.huntingapp.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    AppUserRepository appUserRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtService jwtService;
    @Autowired
    AuthenticationManager authenticationManager;
    public void register(RegisterRequest request) {
        if (appUserRepository.count() == 0) {
            AppUser user = AppUser.builder()
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.ROLE_ADMIN)
                    .active(true)
                    .build();
            appUserRepository.save(user);
            String jwtToken = jwtService.generateToken(user);
        } else {
            throw new DataDuplicateException("Admin user already exists. Registration is not allowed.");
        }
    }
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        if (!appUserRepository.existsByUsername(request.getUsername())) {
            throw new DataNotFoundException("User not found. Please register first.");
        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        AppUser user = appUserRepository.selectByUsername(request.getUsername()).get();
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
