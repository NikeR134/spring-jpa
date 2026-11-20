package ru.netology.persons.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.netology.persons.model.Person;
import ru.netology.persons.model.PersonId;

public interface PersonRepository extends JpaRepository<Person, PersonId> {
    List<Person> findByCityOfLivingIgnoreCase(String city);

    List<Person> findById_AgeLessThanOrderById_AgeAsc(int age);

    Optional<Person> findFirstById_NameAndId_SurnameOrderById_AgeAsc(String name, String surname);
}
