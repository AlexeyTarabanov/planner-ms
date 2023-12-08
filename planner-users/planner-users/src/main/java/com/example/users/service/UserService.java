package com.example.users.service;

import com.example.entity.User;
import com.example.users.repo.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User add(User user) {
        return userRepository.save(user);
    }

    public User update(User user) {
        return userRepository.save(user); // метод save обновляет или создает новый объект, если его не было
    }

    public void deleteByUserId(Long id) {
        userRepository.deleteById(id);
    }

    public void deleteByUserEmail(String email) {
        userRepository.deleteByEmail(email);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id); // т.к. возвращается Optional - можно получить объект методом get()
    }


    public Page<User> findByParams(String username, String password, PageRequest paging) {
        return userRepository.findByParams(username, password, paging);
    }
}
