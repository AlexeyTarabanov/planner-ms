package com.example.todo.controller;

import com.example.entity.Stat;
import com.example.todo.service.StatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
// базовый URI не нужен, т.к. метод только один
public class StatController {

    private final StatService statService;

    public StatController(StatService statService) {
        this.statService = statService;
    }


    // для статистика всгда получаем только одну строку с id=1 (согласно таблице БД)
    @PostMapping("/stat")
    public ResponseEntity<Stat> findByEmail(@RequestBody Long userId) {

        // можно не использовать ResponseEntity, а просто вернуть коллекцию, код все равно будет 200 ОК
        return ResponseEntity.ok(statService.findStat(userId));
    }


}
