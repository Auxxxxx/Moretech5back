package com.example.moretech5back.web.controller;

import com.example.moretech5back.exception.UserNotFoundException;
import com.example.moretech5back.service.UserService;
import com.example.moretech5back.web.httpData.employee.*;
import com.example.moretech5back.web.util.RequestData;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @PostMapping("/list")
    public ResponseEntity<UserListResponse> list() {
        log.info("returning_employee_list");
        var employees = userService.listAll();
        var response = UserListResponse.builder()
                .employees(employees)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/get")
    public ResponseEntity<UserGetResponse> get(
            @RequestData UserGetNameRequest request) {
        log.info("fetching_employee: " + request.getLogin());
        try {
            var user = userService.get(request.getLogin());
            var response = UserGetResponse.builder()
                    .user(user)
                    .build();
            return ResponseEntity.ok(response);
        } catch (UserNotFoundException e) {
            log.error("employee_not_found: " + request.getLogin());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }
    }

    @PostMapping("/get-name")
    public ResponseEntity<UserGetNameResponse> getName(
            @RequestData UserGetNameRequest request) {
        log.info("fetching_name: " + request.getLogin());
        try {
            var name = userService.get(request.getLogin()).getName();
            var response = UserGetNameResponse.builder()
                    .name(name)
                    .build();
            return ResponseEntity.ok(response);
        } catch (UserNotFoundException e) {
            log.error("employee_not_found: " + request.getLogin());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }
    }

    @PostMapping("/put")
    public ResponseEntity<Void> put(
            @RequestData UserPutRequest request) {
        log.info("saving_employee: " + request.getLogin());

        userService.save(
                request.getName(),
                request.getLogin(),
                request.getPassword());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/delete")
    public ResponseEntity<Void> delete(
            @RequestData UserDeleteRequest request) {
        log.info("deleting_employee: " + request.getLogin());
        userService.delete(request.getLogin());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}