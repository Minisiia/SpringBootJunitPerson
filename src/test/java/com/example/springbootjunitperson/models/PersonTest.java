package com.example.springbootjunitperson.models;

import com.example.springbootjunitperson.validators.PersonValidator;
import com.example.springbootjunitperson.validators.PersonValidatorImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    private PersonValidator personValidator = new PersonValidatorImpl();

    private Person person = new Person();

    private Person getPerson() {
        return new Person(1, "Mi",5,"mi@gmail.com");
    }

    @Test
    @DisplayName("Тест для get и set Id")
    public void testGetAndSetId() {
        int id = 1;
        person.setId(id);

        assertEquals(id, person.getId());
    }

    @Test
    @DisplayName("Тест для get и set Name")
    public void testGetAndSetName() {
        String name = "John";
        person.setName(name);

       assertEquals(name, person.getName());
    }

    @Test
    @DisplayName("Тест для get и set Age")
    public void testGetAndSet() {
        int age = 25;
        person.setAge(age);

        assertEquals(age, person.getAge());
    }

    @Test
    @DisplayName("Тест для get и set Email")
    public void testGetAndSetEmail() {
        String email = "john@example.com";
        person.setEmail(email);

        assertEquals(email, person.getEmail());
    }

    // тесты, которые тестируют метод на проверенный тип порожденного исключения из-за невалидных параметров, передаваемых в метод.

    @Test
    @DisplayName("Тест пустого поля Name")
    public void testSetNameInvalidEmpty() {
        person = getPerson();
        person.setName("");
        assertThrows(IllegalArgumentException.class, () -> personValidator.validate(person));
    }

    @Test
    @DisplayName("Тест поле Name 1 символ")
    public void testSetNameInvalidLength() {
        person = getPerson();
        person.setName("A");
        assertThrows(IllegalArgumentException.class, () -> personValidator.validate(person));
    }
    @Test
    @DisplayName("Тест поле Name 31 символ")
    public void testSetNameMaxLengthMoreThan30() {
        person = getPerson();
        person.setName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        assertThrows(IllegalArgumentException.class, () -> personValidator.validate(person));
    }

    @Test
    @DisplayName("Тест поле Age отрицательное")
    public void testSetAgeInvalidNegative() {
        person = getPerson();
        person.setAge(-5);

        assertThrows(IllegalArgumentException.class, () -> personValidator.validate(person));

//        assertThrows(IllegalArgumentException.class, () ->
//        person.setAge(-1));
    }

    @Test
    @DisplayName("Тест на неправильный email")
    public void testSetEmailInvalidFormat() {
        person = getPerson();
        person.setEmail("invalid-email");
        assertThrows(IllegalArgumentException.class, () -> personValidator.validate(person));
    }

}