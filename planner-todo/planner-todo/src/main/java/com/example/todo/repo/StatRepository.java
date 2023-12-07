package com.example.todo.repo;

import com.example.entity.Stat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatRepository extends CrudRepository<Stat, Long> {

    // всегда получаем только 1 объект,
    // т.к. 1 пользователь содержит только 1 строку статистики (связь "один к одному")
    Stat findByUserId(Long id);
}
