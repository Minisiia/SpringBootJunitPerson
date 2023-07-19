package com.example.springbootjunitperson.validators;

import com.example.springbootjunitperson.models.Person;
import org.springframework.stereotype.Component;


public interface PersonValidator {
    void validate(Person person);
}
