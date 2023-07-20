package com.example.springbootjunitperson.services;

import com.example.springbootjunitperson.models.Person;
import com.example.springbootjunitperson.repositories.PeopleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PeopleServiceTest {

    @Mock
    private PeopleRepository peopleRepository;

    @InjectMocks
    private PeopleService peopleService;

//    @Test
//    public void testFindAll() {
//
//        List<Person> personList = new ArrayList<>();
//        personList.add(new Person(1, "John", 18, "1@l.com"));
//        personList.add(new Person(2, "Deleted John", 18, "1@l.com"));
//
//        // Создание заглушки для возвращаемого значения peopleRepository.findAll()
//        when(peopleRepository.findAll()).thenReturn(personList);
//
//        // Вызов метода findAll() в сервисном классе
//        List<Person> actualPeople = peopleService.findAll();
//
//        // Проверка, что возвращаемое значение соответствует ожидаемому
//        assertEquals(personList, actualPeople);
//
//        verify(peopleRepository, times(1)).findAll();
//
//    }

    /*
    @ParameterizedTest - выполнение теста несколько раз с разными аргументами.
    Мы должны объявить по крайней мере один источник аргументов,
    предоставляющий аргументы для каждого вызова, которые будут использоваться в тестовом методе.

     Аннотация @MethodSource позволяет указать статический метод, который возвращает поток (Stream) значений,
     которые будут использоваться для параметризации теста. Когда параметризированный тест запускается,
     JUnit 5 вызывает указанный метод для получения потока значений,
     и каждое значение из потока используется в отдельном запуске теста.
    */
    @ParameterizedTest
    @MethodSource("providePeopleList")
    void testFindAll(List<Person> personList) {

        System.out.println("Running testFindAll with parameters: " + personList);
        // Создание заглушки для возвращаемого значения peopleRepository.findAll()
        when(peopleRepository.findAll()).thenReturn(personList);

        // Вызов метода findAll() в сервисном классе
        List<Person> actualPeople = peopleService.findAll();

        // Проверка, что возвращаемое значение соответствует ожидаемому
        assertEquals(personList, actualPeople);

        verify(peopleRepository, times(1)).findAll();
    }
    static Stream<List<Person>> providePeopleList() {
        // Возвращаем стрим с различными списками объектов Person
        List<Person> personList1 = new ArrayList<>();
        personList1.add(new Person(1, "John", 18, "1@l.com"));
        personList1.add(new Person(2, "Deleted John", 18, "1@l.com"));

        List<Person> personList2 = new ArrayList<>();
        personList2.add(new Person(3, "Alice", 25, "2@l.com"));
        personList2.add(new Person(4, "Bob", 30, "3@l.com"));

        return Stream.of(personList1, personList2);
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

//    @Test
//    public void testSave() {
//        int personId = 1;
//
//        Person person = new Person(personId, "John", 18, "1@l.com");
//        // Вызов метода save() в сервисном классе
//        when(peopleRepository.save(Mockito.any(Person.class))).thenReturn(person);
//        Person savedPerson = peopleService.save(person);
//
//       assertNotNull(savedPerson);
//
//        // Проверка, что метод peopleRepository.save() был вызван один раз с переданным person
//        verify(peopleRepository, times(1)).save(person);
//    }
//
//
//    @Test
//    public void testUpdate() {
//        int personId = 1;
//        Person person = new Person(personId, "John", 18, "1@l.com");
//        Person updatedPerson = new Person(1,"Updated John", 20, "1@l.com");
//
//        when(peopleRepository.findById(personId)).thenReturn(Optional.of(person));
//        when(peopleRepository.save(person)).thenReturn(person);
//        // Вызов метода update() в сервисном классе
//        System.out.println(person.getName());
//        Person updatedPerson2 = peopleService.update(personId, updatedPerson);
//        System.out.println(person.getName());
//        System.out.println(updatedPerson2.getName());
//       assertEquals(updatedPerson2.getName(), person.getName());
//    }
//
//    @Test
//    public void testDelete() {
//        int personId = 1;
//
//        Person person = new Person(1, "John", 18, "1@l.com");
////        when(peopleRepository.findAll()).thenReturn(Collections.singletonList(new Person(2, "Deleted John", 18, "1@l.com")));
////        when(peopleRepository.findById(personId)).thenReturn(Optional.of(person));
////        when(peopleRepository.save(person)).thenReturn(person);
////        doNothing().when(peopleRepository).delete(person);
//
//        System.out.println(person.getName());
//
//        assertAll(() -> peopleService.delete(personId));
//
//        System.out.println(person.getName());
//
//        // Проверка, что метод peopleRepository.deleteById() был вызван один раз с переданным personId
//       verify(peopleRepository, times(1)).deleteById(personId);
//    }

}