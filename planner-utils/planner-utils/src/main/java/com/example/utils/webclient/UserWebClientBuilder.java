package com.example.utils.webclient;

import com.example.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class UserWebClientBuilder {

    private static final String baseUrl = "http://localhost:8765/planner-users/user/";

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

            User user = WebClient.create(baseUrl)
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
}
