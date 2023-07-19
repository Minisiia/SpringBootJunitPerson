## SpringBootJunitPerson
Create 2 tests that test the method for a checked type thrown exception for invalid parameters passed to the method. Create 2 tests that will test the retrieval of information from the database. Create 2 tests that will check the operation of the controllers.

## 7. Spring 5. Junit

## Завдання 2

Покрити всі методи проєкту, які мають нетривіальну логіку, тестами.

## Завдання 4

Створити 2 тести, які тестують метод на перевірений тип породженого винятку за невалідних параметрів, що були передані в метод. Створити 2 тести, які тестуватимуть отримання інформації з БД. Створити 2 тести, які перевірять роботу контролерів.

## Заметки

### @Mock и @InjectMocks

**@Mock** - это аннотация, которая используется для создания заглушек (mock-объектов) для зависимостей класса, который тестируется. Она создает заглушку для класса PeopleRepository в вашем случае. Заглушка PeopleRepository имеет фиктивные реализации методов и может быть настроена для возвращения ожидаемых результатов во время тестирования.

**@InjectMocks** - это аннотация, которая используется для создания экземпляра класса, который тестируется, и автоматической вставки (внедрения) созданных заглушек в зависимости этого класса. Она создает экземпляр класса PeopleService и автоматически внедряет заглушку PeopleRepository в поле peopleRepository внутри PeopleService.

### @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

Аннотация @AutoConfigureTestDatabase указывает настройки базы данных для автоматической конфигурации тестовой базы данных в тестовом окружении.

Параметр replace аннотации @AutoConfigureTestDatabase определяет, должна ли тестовая база данных заменить основную базу данных или нет. Значение AutoConfigureTestDatabase.Replace.NONE указывает, что тестовая база данных не должна заменять основную базу данных.

Когда replace установлено в AutoConfigureTestDatabase.Replace.NONE:

- Если в вашем приложении настроена основная база данных, тестовая база данных не будет автоматически создаваться или использоваться. Вместо этого будет использована настроенная основная база данных.
- Это полезно, когда вам нужно тестировать код в реальной базе данных, а не в памяти или в фиктивной базе данных.

### .andExpect(status())

- .is2xxSuccessful(): Проверяет, что статус ответа находится в диапазоне 2xx (успешный).

- .isOk(): Проверяет, что статус ответа равен 200 (ОК).

- .isCreated(): Проверяет, что статус ответа равен 201 (Создан).

- .isAccepted(): Проверяет, что статус ответа равен 202 (Принят).

- .isNoContent(): Проверяет, что статус ответа равен 204 (Нет содержимого).

- .is3xxRedirection(): Проверяет, что статус ответа находится в диапазоне 3xx (перенаправление).

- .isFound(): Проверяет, что статус ответа равен 302 (Найдено).

- .is3xxRedirection(): Проверяет, что статус ответа находится в диапазоне 3xx (перенаправление).

- .is4xxClientError(): Проверяет, что статус ответа находится в диапазоне 4xx (ошибка клиента).

- .isBadRequest(): Проверяет, что статус ответа равен 400 (Неверный запрос).

- .isUnauthorized(): Проверяет, что статус ответа равен 401 (Неавторизован).

- .isForbidden(): Проверяет, что статус ответа равен 403 (Запрещено).

- .isNotFound(): Проверяет, что статус ответа равен 404 (Не найдено).

- .is5xxServerError(): Проверяет, что статус ответа находится в диапазоне 5xx (ошибка сервера).

- .isInternalServerError(): Проверяет, что статус ответа равен 500 (Внутренняя ошибка сервера).

### ParameterizedTest

@ParameterizedTest - выполнение теста несколько раз с разными аргументами.

Мы должны объявить по крайней мере один источник аргументов, предоставляющий аргументы для каждого вызова, которые будут использоваться в тестовом методе.

Аннотация @MethodSource позволяет указать статический метод, который возвращает поток (Stream) значений, которые будут использоваться для параметризации теста.

Когда параметризированный тест запускается, JUnit 5 вызывает указанный метод для получения потока значений, и каждое значение из потока используется в отдельном запуске теста.

```
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
```



