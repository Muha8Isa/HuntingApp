package jaktia.huntingapp.entity;

import jaktia.huntingapp.Enum.Role;
import jaktia.huntingapp.exceptions.NotValidPasswordException;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;


@Data
@ToString(exclude = "password")
@NoArgsConstructor
@Builder
@Entity //Maps the class to database
public class AppUser implements UserDetails {
    @Id
    @Column(name = "username",updatable = false, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private boolean active = true;
    @CreationTimestamp // LocalDate.now();
    private LocalDate regDate;

    public AppUser(String username, String password, Role role, boolean active) {
        this.username = username;
        setPassword(password);
        this.role = role;
        this.active = active;
    }

    public AppUser(String username, String password, Role role, boolean active, LocalDate regDate) {
        this.username = username;
        setPassword(password);
        this.role = role;
        this.active = active;
        this.regDate = regDate;
    }

   /* public void setPassword(String password) {
        if (password.length() < 6 || password.length() > 18)
            throw new NotValidPasswordException("Password length must be between 6 and 18");
        this.password = password;
    }*/

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
