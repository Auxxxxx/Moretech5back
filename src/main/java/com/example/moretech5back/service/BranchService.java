package com.example.moretech5back.service;

import com.example.moretech5back.exception.UserNotFoundException;
import com.example.moretech5back.repository.UserRepository;
import com.example.moretech5back.web.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> listAll() {
        return userRepository.findAll();
    }

    public void save(String name, String login, String password) {
        User user;
        Optional<User> existing = userRepository.findByLogin(login);
        if (existing.isPresent()) {
            user = existing.get();
            user.setPassword(password);
            user.setName(name);
        } else {
            user = User.builder()
                    .login(login)
                    .password(password)
                    .name(name)
                    .build();
        }
        userRepository.save(user);
    }

    public User get(String login) throws UserNotFoundException {
        return userRepository.findByLogin(login).orElseThrow(UserNotFoundException::new);
    }

    public String getName(String login) throws UserNotFoundException {
        Optional<User> employee = userRepository.findByLogin(login);
        return employee.map(User::getName).orElseThrow(UserNotFoundException::new);
    }

    @Transactional
    public void delete(String login) {
        userRepository.deleteByLogin(login);
    }
}
