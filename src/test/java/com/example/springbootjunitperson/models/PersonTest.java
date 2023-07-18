package com.example.springbootjunitperson.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    private Person person= new Person();

    @Test
    public void testGetAndSetId() {
        int id = 1;
        person.setId(id);

        assertEquals(id, person.getId());
    }

    @Test
    public void testGetAndSetName() {
        String name = "John";
        person.setName(name);

       assertEquals(name, person.getName());
    }

    @Test
    public void testGetAndSetAge() {
        int age = 25;
        person.setAge(age);

        assertEquals(age, person.getAge());
    }

    @Test
    public void testGetAndSetEmail() {
        String email = "john@example.com";
        person.setEmail(email);

        assertEquals(email, person.getEmail());
    }

    // тесты, которые тестируют метод на проверенный тип порожденного исключения из-за невалидных параметров, передаваемых в метод.

    @Test
    public void testSetNameInvalidEmpty() {
        person = getPerson();
        person.setName("");
        assertThrows(IllegalArgumentException.class, person::validate);
    }

    @Test
    public void testSetNameInvalidLength() {
        person = getPerson();
        person.setName("A");
        assertThrows(IllegalArgumentException.class, person::validate);
    }
    @Test
    public void testSetNameMaxLengthMoreThan30() {
        person = getPerson();
        person.setName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        assertThrows(IllegalArgumentException.class, person::validate);
    }

    @Test
    public void testSetAgeInvalidNegative() {
        person = getPerson();
        person.setAge(-5);

        assertThrows(IllegalArgumentException.class, person::validate);

//        assertThrows(IllegalArgumentException.class, () ->
//        person.setAge(-1));
    }

    @Test
    public void testSetEmailInvalidFormat() {
        person = getPerson();
        person.setEmail("invalid-email");
        assertThrows(IllegalArgumentException.class, person::validate);
    }

    private Person getPerson() {
        return new Person(1, "Mi",5,"mi@gmail.com");
    }

}