package com.example.moretech5back.web.controller;

import com.example.moretech5back.exception.ATMNotFoundException;
import com.example.moretech5back.exception.BranchNotFoundException;
import com.example.moretech5back.service.ATMService;
import com.example.moretech5back.web.httpData.atm.ATMGetRequest;
import com.example.moretech5back.web.httpData.atm.ATMGetResponse;
import com.example.moretech5back.web.httpData.atm.ATMListResponse;
import com.example.moretech5back.web.httpData.branch.*;
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
@RequestMapping(path = "/atm", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class ATMController {
    private final ATMService atmService;

    @PostMapping("/list")
    public ResponseEntity<ATMListResponse> list() {
        log.info("returning_atm_list");
        var atms = atmService.listAll();
        var response = ATMListResponse.builder()
                .atms(atms)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/get")
    public ResponseEntity<ATMGetResponse> get(
            @RequestData ATMGetRequest request) {
        log.info("fetching_atm: " + request.getId());
        try {
            var atm = atmService.get(request.getId());
            var response = ATMGetResponse.builder()
                    .atm(atm)
                    .build();
            return ResponseEntity.ok(response);
        } catch (ATMNotFoundException e) {
            log.error("atm_not_found: " + request.getId());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }
    }

}