package com.example.springbootjunitperson.repositories;

import com.example.springbootjunitperson.models.Person;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/*
Параметр replace аннотации @AutoConfigureTestDatabase определяет,
должна ли тестовая база данных заменить основную базу данных или
нет.
Значение AutoConfigureTestDatabase.Replace.NONE указывает, что
тестовая база данных не должна заменять основную базу данных.
 */

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PeopleRepositoryTest {
    @Autowired
    PeopleRepository peopleRepository;

    @Test
    public void savePeopleTest() {
        Person person = new Person("John", 18, "ram@gmail.com");
        peopleRepository.save(person);

        assertTrue(person.getId() > 0);
     //   Assumptions.assumeTrue(person.getId() < 0);
    }
    @Test
    public void getPersonTest(){
        int personId = 1;

        Person person = getPersonById(personId);

        assertEquals(personId,person.getId());

    }

    @Test
    public void getPeopleListTest(){

        List<Person> peopleList = peopleRepository.findAll();

        assertTrue(peopleList.size() > 0);
    }



    @Test
    public void updateEmployeeTest(){

        Person person = getPersonById(1);
       // System.out.println(person);
        person.setEmail("ram@gmail.com");
        Person personUpdated =  peopleRepository.save(person);
        //System.out.println(personUpdated);
        assertEquals(personUpdated.getEmail(),"ram@gmail.com");
    }

    @Test
    public void deleteEmployeeTest(){

        Person person = getPersonById(1);
        System.out.println(person);
        peopleRepository.delete(person);

        Person emptyPerson = null;

      //  Optional<Person> personOptional = peopleRepository.findByEmail("rar@gmail.com");
        Optional<Person> personOptional = peopleRepository.findById(1);
        System.out.println(personOptional);
        if(personOptional.isPresent()){
            emptyPerson = personOptional.get();
        }
        System.out.println(emptyPerson);
        assertNull(emptyPerson);
    }


    private Person getPersonById(int personId) {
        return peopleRepository.findById(personId)
                .orElseThrow(() -> new RuntimeException("Person not found"));
    }
}