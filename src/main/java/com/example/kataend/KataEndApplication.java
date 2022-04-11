package com.example.kataend;

import com.example.kataend.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class KataEndApplication {
    private static final String USERS_GET = "http://94.198.50.185:7081/api/users";
    private static final String USER_POST = "http://94.198.50.185:7081/api/users";
    private static final String USER_PATCH = "http://94.198.50.185:7081/api/users";
    private static final String USER_DELETE = "http://94.198.50.185:7081/api/users/";
    private static List<String> cookies;

    RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        SpringApplication.run(KataEndApplication.class, args);
        KataEndApplication kataEndApplication = new KataEndApplication();

        //List of Users
        kataEndApplication.getListOfUsers();
        //add User
        User user3 = new User(3L, "James", "Brown", (byte) 26);
        kataEndApplication.getNewUser(user3);

        //Patch User
        user3.setName("Thomas");
        user3.setLastName("Shelby");
        kataEndApplication.getUpdateUser(user3);
        //Delete User
        kataEndApplication.getDeleteUser(user3.getId());
    }


    public HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }



    public void getListOfUsers() {
        HttpHeaders headers = getHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(USERS_GET,
                HttpMethod.GET,
                entity,
                String.class);
        cookies = response.getHeaders().get("Set-Cookie");

        for (String c : cookies) {
            System.out.println(c);

        }

        String result = response.getBody();
        System.out.println(result);
    }

    public void getNewUser(User user) {
        HttpHeaders headers = getHeaders();
        headers.set("Cookie", cookies.stream().collect(Collectors.joining(";")));
        HttpEntity<User> entity = new HttpEntity<>(user, headers);

        ResponseEntity<String> response = restTemplate.exchange(USER_POST,
                HttpMethod.POST,
                entity,
                String.class);

        System.out.println(response.getBody());
    }

    public void getUpdateUser(User user) {
        HttpHeaders headers = getHeaders();
        headers.set("Cookie", cookies.stream().collect(Collectors.joining(";")));
        HttpEntity<User> entity = new HttpEntity<>(user, headers);

        ResponseEntity<String> response = restTemplate.exchange(USER_PATCH,
                HttpMethod.PUT,
                entity,
                String.class);

        System.out.println(response.getBody());
    }

    public void getDeleteUser(long id) {
        HttpHeaders headers = getHeaders();
        headers.set("Cookie", cookies.stream().collect(Collectors.joining(";")));
        HttpEntity<User> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(USER_DELETE + id,
                HttpMethod.DELETE,
                entity,
                String.class);

        System.out.println(response.getBody());

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
