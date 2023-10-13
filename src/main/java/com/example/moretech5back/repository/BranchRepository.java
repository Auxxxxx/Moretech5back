package com.example.moretech5back.repository;

import com.example.moretech5back.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);

    List<User> findByLoginIn(List<String> logins);

    void deleteByLogin(String login);

}
