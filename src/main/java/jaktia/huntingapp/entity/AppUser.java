package jaktia.huntingapp.entity;

import jaktia.huntingapp.Enum.Role;
import jaktia.huntingapp.exceptions.DataDuplicateException;
import jaktia.huntingapp.exceptions.DataNotFoundException;
import jaktia.huntingapp.exceptions.NotValidPasswordException;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;

import java.nio.file.AccessDeniedException;
import java.time.LocalDate;


@Data
@ToString(exclude = "password")
@NoArgsConstructor

@Entity //Map the class to database
public class AppUser {
    @Id
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false, length = 18)
    private String password;

    private Role role;

    private boolean active;
    @CreationTimestamp // LocalDate.now();
    private LocalDate regDate;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id")
    private Person person;

    public AppUser(String username, String password, Role role, boolean active) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.active = active;
    }

    public AppUser(String username, String password, Role role, boolean active, LocalDate regDate, Person person) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.active = active;
        this.regDate = regDate;
        this.person = person;
    }
    public void addPerson(Person person, Role role) throws AccessDeniedException {
        if (role != Role.ROLE_ADMIN) throw new AccessDeniedException("Only Admins can add users!");
        if(this.person != null){
            throw new DataDuplicateException("Duplicated data!");
        }
        this.person = person;
    }

    public void removePerson(Person person, Role role) throws AccessDeniedException{
        if (role != Role.ROLE_ADMIN) throw new AccessDeniedException("Only Admins can remove users!");
        if(this.person == null){
            throw new DataNotFoundException("Data not found!");
        }
        if (this.person == person)
        this.person = null;
    }

    public void setPassword(String password) {
        if(password.length() < 6 || password.length() > 18) throw new NotValidPasswordException("Password length must be between 6 and 18");
        this.password = password;
    }
}
