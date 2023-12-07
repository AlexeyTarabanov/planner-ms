package com.example.todo.service;

import com.example.entity.Priority;
import com.example.todo.repo.PriorityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// всегда нужно создавать отдельный класс Service для доступа к данным, даже если кажется,
// что мало методов или это все можно реализовать сразу в контроллере
// Такой подход полезен для будущих доработок и правильной архитектуры (особенно, если работаете с транзакциями)
@Service

// все методы класса должны выполниться без ошибки, чтобы транзакция завершилась
// если в методе возникнет исключение - все выполненные операции из данного метода откатятся (Rollback)
@Transactional
public class PriorityService {

    // сервис имеет право обращаться к репозиторию (БД)
    private final PriorityRepository repository;

    public PriorityService(PriorityRepository repository) {
        this.repository = repository;
    }

    public List<Priority> findAll(Long userId) {
        return repository.findByUserIdOrderByIdAsc(userId);
    }

    // метод save обновляет или создает новый объект, если его не было
    public Priority add(Priority priority) {
        return repository.save(priority);
    }

    public Priority update(Priority priority) {
        return repository.save(priority);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    // т.к. возвращается Optional - можно получить объект методом get()
    public Priority findById(Long id) {
        return repository.findById(id).get();
    }

    public List<Priority> find(String title, Long userId) {
        return repository.findByTitle(title, userId);
    }

}
