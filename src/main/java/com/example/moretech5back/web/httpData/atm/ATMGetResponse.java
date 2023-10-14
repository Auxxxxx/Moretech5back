package com.example.moretech5back.web.httpData.atm;

import com.example.moretech5back.model.ATM;
import com.example.moretech5back.model.Branch;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class ATMGetResponse {
    private ATM atm;
}