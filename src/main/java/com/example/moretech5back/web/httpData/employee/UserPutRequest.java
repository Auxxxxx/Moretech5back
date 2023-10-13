package com.example.moretech5back.web.httpData.employee;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Data
@Builder
@Jacksonized
public class UserPutRequest {
    private String login;
    private String name;
    private String password;
}
