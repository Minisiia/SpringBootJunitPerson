package com.example.springbootjunitperson.models;

import jakarta.persistence.*;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.*;

import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "Person")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    @Column(name = "name")
    private String name;

    @Min(value = 0, message = "Age should be greater than 0")
    @Column(name = "age")
    private int age;

    @Column(name = "email")
    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email;

    public Person() {

    }

//    public Person(String name, int age) {
//        this.name = name;
//        this.age = age;
//    }


    public Person(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public Person(int id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
//        if (age <= 0) {
//            throw new IllegalArgumentException("Age should be greater than 0");
//        }
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return getId() == person.getId() && getAge() == person.getAge() && Objects.equals(getName(), person.getName()) && Objects.equals(getEmail(), person.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getAge(), getEmail());
    }

/*
В этом примере мы используем ValidatorFactory и Validator из пакета javax.validation
для получения экземпляра валидатора.
Затем мы вызываем метод validate(this), чтобы выполнить валидацию текущего объекта Person.
Если есть нарушения валидации (violations), мы строим сообщение об ошибке,
содержащее текст каждого нарушения, и выбрасываем IllegalArgumentException.
 */
//    public void validate() {
//        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//        Validator validator = factory.getValidator();
//
//        Set<ConstraintViolation<Person>> violations = validator.validate(this);
//        if (!violations.isEmpty()) {
//            StringBuilder errorMessage = new StringBuilder();
//            for (ConstraintViolation<Person> violation : violations) {
//                errorMessage.append(violation.getMessage()).append("\n");
//            }
//            throw new IllegalArgumentException(errorMessage.toString());
//        }
//    }
}
