package jaktia.huntingapp.repository;

import jaktia.huntingapp.Enum.Role;
import jaktia.huntingapp.entity.AppUser;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository //Optional
public interface AppUserRepository extends CrudRepository<AppUser, String> {

    // select * from app_user where username = 1?
    // Optional<AppUser> findByUsername(String username);

    @Query("select a from AppUser a where a.username = :un")
    Optional<AppUser> selectByUsername(@Param("un") String username);

    Optional<AppUser> findByRole(Role role);
    Boolean existsByUsername(String username);

    // List<AppUser> findAllByRegDateBetween(LocalDate from, LocalDate to);

    @Query("select a from AppUser a where a.regDate >= :from and a.regDate <= :to") // Named parameters :from :to
    List<AppUser> selectByRegistrationDate(@Param("from") LocalDate from, @Param("to") LocalDate to);

    /** @Query("select a from AppUser a where a.regDate >= ?1 and a.regDate <= ?2") // positional parameter ?
     List<AppUser> selectByRegistrationDate(LocalDate from, LocalDate to);  // Another example, but the previous one with named parameters (:from) is more readable. **/

    @Modifying // To modify the database.
    @Query("update AppUser a set a.password = :pwd where a.username = :un")
    void resetPassword(@Param("un") String username, @Param("pwd") String password);

    @Modifying
    @Query("update AppUser a set a.active =:active where a.username = :username")
    void disableUserByUsername(@Param("username") String username, @Param("active") boolean active);

    boolean existsByRole(Role role);

}
