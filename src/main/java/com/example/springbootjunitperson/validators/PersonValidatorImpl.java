package com.example.springbootjunitperson.validators;

import com.example.springbootjunitperson.models.Person;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class PersonValidatorImpl implements PersonValidator {

    /*
    В этом примере мы используем ValidatorFactory и Validator из пакета javax.validation
    для получения экземпляра валидатора.
    Затем мы вызываем метод validate(this), чтобы выполнить валидацию текущего объекта Person.
    Если есть нарушения валидации (violations), мы строим сообщение об ошибке,
    содержащее текст каждого нарушения, и выбрасываем IllegalArgumentException.
    */
    @Override
    public void validate(Person person) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Person>> violations = validator.validate(person);
        if (!violations.isEmpty()) {
            StringBuilder errorMessage = new StringBuilder();
            for (ConstraintViolation<Person> violation : violations) {
                errorMessage.append(violation.getMessage()).append("\n");
            }
            throw new IllegalArgumentException(errorMessage.toString());
        }
    }
}
