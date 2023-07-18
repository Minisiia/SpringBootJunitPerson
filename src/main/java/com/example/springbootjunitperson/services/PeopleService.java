package com.example.springbootjunitperson.services;

import com.example.springbootjunitperson.models.Person;
import com.example.springbootjunitperson.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

/**
 * @author Neil Alishev
 */
@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findOne(int id) {
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElse(null);
    }

    @Transactional
    public Person save(Person person) {
        peopleRepository.save(person);
        return person;
    }

    @Transactional
    public Person update(int id, Person updatedPerson) {
        updatedPerson.setId(id);
//        peopleRepository.save(updatedPerson);
        Optional<Person> optionalPerson = peopleRepository.findById(id);
        if (optionalPerson.isPresent()) {
            Person existingPerson = optionalPerson.get();
            existingPerson.setName(updatedPerson.getName());  // Обновление свойства name
            existingPerson.setAge(updatedPerson.getAge());
            existingPerson.setEmail(updatedPerson.getEmail());
            peopleRepository.save(existingPerson);
        }
        return updatedPerson;
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);

    }
}
