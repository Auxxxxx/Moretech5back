package com.example.moretech5back.web.httpData.atm;

import com.example.moretech5back.model.ATM;
import com.example.moretech5back.model.Branch;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Data
@Builder
@Jacksonized
public class ATMListResponse {
    private List<ATM> atms;
}

