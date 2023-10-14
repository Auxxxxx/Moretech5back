package com.example.moretech5back.web.httpData.atm;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class ATMGetRequest {
    private Long id;
}