package com.example.springbootjunitperson.services;

import com.example.springbootjunitperson.models.Person;
import com.example.springbootjunitperson.repositories.PeopleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PeopleServiceTest {

    @Mock
    private PeopleRepository peopleRepository;

    @InjectMocks
    private PeopleService peopleService;

    @Test
    public void testFindAll() {

        List<Person> personList = new ArrayList<>();
        personList.add(new Person(1, "John", 18, "1@l.com"));
        personList.add(new Person(2, "Deleted John", 18, "1@l.com"));

        // Создание заглушки для возвращаемого значения peopleRepository.findAll()
        when(peopleRepository.findAll()).thenReturn(personList);

        // Вызов метода findAll() в сервисном классе
        List<Person> actualPeople = peopleService.findAll();

        // Проверка, что возвращаемое значение соответствует ожидаемому
        assertEquals(personList, actualPeople);

        verify(peopleRepository, times(1)).findAll();

    }

    @Test
    public void testFindOne() {
        int personId = 1;

        Person person = new Person(personId, "John", 18, "1@l.com");

        // Создание заглушки для возвращаемого значения peopleRepository.findById()
        Optional<Person> optionalPerson = Optional.of(new Person(personId, "John", 18, "1@l.com"));
        when(peopleRepository.findById(personId)).thenReturn(optionalPerson);

        // Вызов метода findOne() в сервисном классе
        Person actualPerson = peopleService.findOne(personId);

        // Проверка, что возвращаемое значение соответствует ожидаемому
        assertEquals(person, actualPerson);

        // Проверка, что метод peopleRepository.findById() был вызван один раз с переданным personId
        verify(peopleRepository, times(1)).findById(personId);
    }

    @Test
    public void testSave() {
        int personId = 1;

        Person person = new Person(personId, "John", 18, "1@l.com");
        // Вызов метода save() в сервисном классе
        when(peopleRepository.save(Mockito.any(Person.class))).thenReturn(person);
        Person savedPerson = peopleService.save(person);

       assertNotNull(savedPerson);

        // Проверка, что метод peopleRepository.save() был вызван один раз с переданным person
        verify(peopleRepository, times(1)).save(person);
    }


    @Test
    public void testUpdate() {
        int personId = 1;
        Person person = new Person(personId, "John", 18, "1@l.com");
        Person updatedPerson = new Person(1,"Updated John", 20, "1@l.com");

        when(peopleRepository.findById(personId)).thenReturn(Optional.of(person));
        when(peopleRepository.save(person)).thenReturn(person);
        // Вызов метода update() в сервисном классе
        System.out.println(person.getName());
        Person updatedPerson2 = peopleService.update(personId, updatedPerson);
        System.out.println(person.getName());
        System.out.println(updatedPerson2.getName());
       assertEquals(updatedPerson2.getName(), person.getName());
    }

    @Test
    public void testDelete() {
        int personId = 1;

        Person person = new Person(1, "John", 18, "1@l.com");
//        when(peopleRepository.findAll()).thenReturn(Collections.singletonList(new Person(2, "Deleted John", 18, "1@l.com")));
//       //int personId = 2;
//
//        // Вызов метода delete() в сервисном классе
//        peopleService.delete(personId);
//
//        when(peopleRepository.findById(personId)).thenReturn(Optional.of(person));
//        doNothing().when(peopleRepository).delete(person);

        assertAll(() -> peopleService.delete(personId));


        // Проверка, что метод peopleRepository.deleteById() был вызван один раз с переданным personId
       verify(peopleRepository, times(1)).deleteById(personId);
    }

}