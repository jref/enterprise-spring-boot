package com.ua.codespace.repository;


import com.ua.codespace.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomUserRepository {
    Page<User> findUsersByEmailProvider(String emailProvider, Pageable pageable);
}
