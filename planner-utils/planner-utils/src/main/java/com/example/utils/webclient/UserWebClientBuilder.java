package com.example.utils.webclient;

import com.example.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
public class UserWebClientBuilder {

    private static final String BASE_URL_USER = "http://localhost:8765/planner-users/user/";

    /**
     *
     * Проверяет существование пользователя по его идентификатору.
     *
     * @param userId идентификатор пользователя
     * @return true, если пользователь существует; false в противном случае
     */
    public boolean userExists(Long userId){
         try {

            User user = WebClient.create(BASE_URL_USER)
                    .post()
                    .uri("id")
                    .bodyValue(userId)
                    .retrieve()
                    .bodyToFlux(User.class)
                    .blockFirst();

            if (user != null) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Если статус не был успешным или возникла ошибка, считаем, что пользователь не существует
        return false;
    }

    /**
     * Проверяет асинхронно существование пользователя по идентификатору.
     *
     * @param userId Идентификатор пользователя.
     * @return Flux, представляющий результат асинхронной проверки существования пользователя.
     */
    public Flux<User> userExistsAsync(Long userId) {
        // Создание Flux для запроса к удаленному сервису с использованием WebClient

        return WebClient.create(BASE_URL_USER)
                .post()  // Определение HTTP-метода (в данном случае, POST)
                .uri("id")  // Установка URI-пути
                .bodyValue(userId)  // Установка тела запроса (в данном случае, идентификатор пользователя)
                .retrieve()  // Выполнение запроса и получение ответа
                .bodyToFlux(User.class);
    }

}
