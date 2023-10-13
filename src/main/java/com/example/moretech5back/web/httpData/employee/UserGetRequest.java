package com.example.moretech5back.web.httpData.employee;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class EmployeeGetRequest {
    private String login;
}