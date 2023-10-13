package com.example.moretech5back.service;

import com.example.moretech5back.repository.UserRepository;
import com.example.moretech5back.web.model.User;
import lombok.AllArgsConstructor;
import online.mdfactory.backend.exception.EmployeeNotFoundException;
import online.mdfactory.backend.model.Employee;
import online.mdfactory.backend.model.OperationGroup;
import online.mdfactory.backend.repository.EmployeeRepository;
import online.mdfactory.backend.security.AuthenticationService;
import online.mdfactory.backend.security.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> listAll() {
        return userRepository.findAll();
    }

    public void save(String name, String login, String password, List<Long> operationGroupIds) {
        User user;
        Optional<User> existing = userRepository.findByLogin(login);
        if (existing.isPresent()) {
            user = existing.get();
            user.setPassword(password);
        } else {
            user = User.builder()
                    .login(login)
                    .password(password)
                    .build();
        }
        userRepository.save(user);
    }

    public User get(String login) throws EmployeeNotFoundException {
        return userRepository.findByLogin(login).orElseThrow(EmployeeNotFoundException::new);
    }

    public String getName(String login) throws EmployeeNotFoundException {
        Optional<Employee> employee = userRepository.findByLogin(login);
        return employee.map(Employee::getName).orElseThrow(EmployeeNotFoundException::new);
    }

    @Transactional
    public void delete(String login) {
        userRepository.deleteByLogin(login);
        authenticationService.deleteByLogin(login);
    }
}
