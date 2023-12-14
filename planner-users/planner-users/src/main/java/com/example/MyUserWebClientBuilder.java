package com.example;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
public class MyUserWebClientBuilder {

    private static final String BASE_URL_DATA = "http://localhost:8765/planner-todo/data/";

    // иниц. начальных данных
    public Flux<Boolean> initUserData(Long userId) {

        return WebClient.create(BASE_URL_DATA)
                .post()
                .uri("init")
                .bodyValue(userId)
                .retrieve()
                .bodyToFlux(Boolean.class);

    }
}
