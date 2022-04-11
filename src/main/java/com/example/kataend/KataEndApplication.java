package com.example.kataend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class KataEndApplication {
    private static final String USER_GET = "http://94.198.50.185:7081/api/users";
    private static final String USER_POST = "http://94.198.50.185:7081/api/users";
    private static final String USER_PATCH = "http://94.198.50.185:7081/api/users";
    private static final String USER_DELETE = "http://94.198.50.185:7081/api/users/";

    RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        SpringApplication.run(KataEndApplication.class, args);



    }



    public void getAllUsers(){
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(USER_GET,String.class);
    }



}

// "http://94.198.50.185:7081/api/users" GET, POST, PUT(PATCH)
// "http://94.198.50.185:7081/api/users/{id}" DELETE
//ResponseEntity
//Все в рамках одной сессии

//Получить список всех пользователей
/*
Когда вы получите ответ на свой первый запрос, вы должны сохранить свой session id, который получен через cookie.
Вы получите его в заголовке ответа set-cookie. Поскольку все действия происходят в рамках
одной сессии, все дальнейшие запросы должны использовать полученный session id
( необходимо использовать заголовок в последующих запросах )
*/

/*Сохранить пользователя с id = 3, name = James, lastName = Brown, age = на ваш выбор.
 В случае успеха вы получите первую часть кода.*/

//Изменить пользователя с id = 3. Необходимо поменять name на Thomas, а lastName на Shelby
//В случае успеха вы получите еще одну часть кода.

//Удалить пользователя с id = 3. В случае успеха вы получите последнюю часть кода.
