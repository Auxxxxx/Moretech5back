package com.example.moretech5back.web.httpData.employee;

import com.example.moretech5back.web.model.User;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;
import online.mdfactory.backend.model.Employee;

import java.util.List;

@Data
@Builder
@Jacksonized
public class UserListResponse {
    private List<User> employees;
}

