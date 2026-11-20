package ru.netology.persons.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.persons.model.Person;
import ru.netology.persons.model.PersonId;
import ru.netology.persons.repository.PersonRepository;

@RestController
public class PersonController {
    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/persons")
    public List<Person> getAll() {
        return personRepository.findAll();
    }

    @GetMapping("/persons/by-city")
    public List<Person> getByCity(@RequestParam String city) {
        return personRepository.findByCityOfLivingIgnoreCase(city);
    }

    @GetMapping("/persons/by-age")
    public List<Person> getByAge(@RequestParam int lessThan) {
        return personRepository.findById_AgeLessThanOrderById_AgeAsc(lessThan);
    }

    @GetMapping("/persons/by-name")
    public ResponseEntity<Person> getByNameAndSurname(@RequestParam String name, @RequestParam String surname) {
        return ResponseEntity.of(personRepository.findFirstById_NameAndId_SurnameOrderById_AgeAsc(name, surname));
    }

    @PostMapping("/persons")
    public Person create(@RequestBody Person person) {
        return personRepository.save(person);
    }

    @PutMapping("/persons")
    public Person update(@RequestBody Person person) {
        return personRepository.save(person);
    }

    @DeleteMapping("/persons")
    public ResponseEntity<Void> delete(@RequestParam String name, @RequestParam String surname, @RequestParam int age) {
        PersonId id = new PersonId(name, surname, age);
        if (!personRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        personRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
