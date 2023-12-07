package com.example.todo.service;

import com.example.entity.Stat;
import com.example.todo.repo.StatRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StatService {

    // сервис имеет право обращаться к репозиторию (БД)
    private final StatRepository repository;

    public StatService(StatRepository repository) {
        this.repository = repository;
    }

    public Stat findStat(Long userId) {
        return repository.findByUserId(userId);
    }

}
