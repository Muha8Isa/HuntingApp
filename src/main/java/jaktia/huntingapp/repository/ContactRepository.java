package jaktia.huntingapp.repository;

import jaktia.huntingapp.Enum.Responsibility;
import jaktia.huntingapp.entity.Contact;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ContactRepository extends CrudRepository<Contact, Integer> {
    Optional<Contact> findByName(String name);
    Optional<Contact> findByEmailIgnoreCase(String email);
    List<Contact> findAllByResponsibility(Responsibility responsibility);

}
