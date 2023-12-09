package com.example.utils.resttemplate;

import com.example.entity.User;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class UserRestBuilder {

    private static final String baseUrl = "http://localhost:8765/planner-users/user";

    /**
     * Проверяет существование пользователя по его идентификатору.
     *
     * @param userId идентификатор пользователя
     * @return true, если пользователь существует; false в противном случае
     */
    public boolean userExists(Long userId){
        // Создаем WebClient
        WebClient webClient = WebClient.create(baseUrl);

        try {
            // Выполняем запрос с использованием WebClient
            ResponseEntity<User> response = webClient
                    .method(HttpMethod.POST)  // Метод запроса
                    .uri("/id")  // Путь запроса
                    .body(BodyInserters.fromValue(userId))  // Передаем тело запроса (в данном случае - userId)
                    .retrieve()  // Получаем ответ
                    .toEntity(User.class)  // Конвертируем ответ в объект User

                    // Блок благополучного завершения запроса
                    .block();  // Блокируем выполнение, ожидая завершения запроса

            if (response != null && response.getStatusCode().is2xxSuccessful()) {
                System.out.println("User с таким id существует");
                // Если статус был успешным (в пределах 2xx), считаем, что пользователь существует
                return true;
            } else {
                System.err.println("User с таким id НЕ существует");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Если статус не был успешным или возникла ошибка, считаем, что пользователь не существует
        return false;
    }
}
