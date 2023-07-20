package com.example.springbootjunitperson.controllers;

import com.example.springbootjunitperson.models.Person;
import com.example.springbootjunitperson.services.PeopleService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BindingResult;
import org.springframework.validation.MapBindingResult;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = PeopleController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class PeopleControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PeopleService peopleService;

    private Person person;

    @BeforeAll
    public static void initBeforeAll(){
        System.out.println("Before All init() method called");
    }

    @AfterAll
    public static void afterAll(){
        System.out.println("After All init() method called");
    }

    @BeforeEach
    public void init() {
        person = new Person(1, "John", 18, "1@l.com");
    }

    @Test
    void index() throws Exception {
        mockMvc.perform(get("/people"))
                .andExpect(status().isOk()).
                andExpect(view().name("people/index"));
    }

    @Test
    void show() throws Exception {
        int personId = 1;

        Mockito.when(peopleService.findOne(personId)).thenReturn(person);

        mockMvc.perform(get("/people/{id}", personId))
                .andExpect(status().isOk())
                .andExpect(view().name("people/show"))
                .andExpect(model().attributeExists("person"))
                .andExpect(model().attribute("person", person))
                .andDo(print());
    }

    /*
    .is2xxSuccessful()) - результат работы будет не просто ОК, а какой-то из 200х статусов.
    */
    @Test
    void newPerson() throws Exception {
        mockMvc.perform(get("/people/new"))
                .andExpect(status()
                        .is2xxSuccessful())
                .andExpect(view().name("people/new"));
    }

    /*
    Метод .andExpect(status().isFound()) используется для проверки статуса HTTP-ответа.
    В данном случае, он проверяет, что статус ответа соответствует коду состояния 302 (Found).
    Код состояния 302 указывает на то, что ресурс был найден,
    но требует дальнейшего перенаправления на другую страницу или URL. */

    @Test
    void create() throws Exception {
        mockMvc.perform(post("/people")
                        .flashAttr("person", person))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/people"))
                .andDo(print());

        Mockito.verify(peopleService).save(person);
    }

    @Test
    void edit() throws Exception {
        int personId = 1;

        Mockito.when(peopleService.findOne(personId)).thenReturn(person);
        mockMvc.perform(get("/people/{id}/edit", personId))
                .andExpect(status().is2xxSuccessful())
                .andExpect(model().attributeExists("person"))
                .andExpect(model().attribute("person", person))
                .andExpect(view().name("people/edit"));
    }

    @Test
    void update() throws Exception {
        int personId = 1;
        Person updatedPerson = new Person(personId, "John", 20, "1@l.com");
        Mockito.when(peopleService.findOne(personId)).thenReturn(person);

        mockMvc.perform(patch("/people/{id}", personId)
                .flashAttr("person", updatedPerson))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/people"))
                .andDo(print());

        Mockito.verify(peopleService).update(personId, updatedPerson);
    }

    /*
    .andExpect(redirectedUrl("/people"))
     */
    @Test
    void delete() throws Exception {
        int personId = 1;
        Mockito.when(peopleService.findOne(personId)).thenReturn(person);
        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete("/people/{id}", personId)
                        .flashAttr("person", person))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/people"))
                .andDo(print());

        Mockito.verify(peopleService).delete(personId);
    }
}