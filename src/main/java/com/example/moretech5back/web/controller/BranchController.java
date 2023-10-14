package com.example.moretech5back.web.controller;

import com.example.moretech5back.exception.BranchNotFoundException;
import com.example.moretech5back.service.BranchService;
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
@RequestMapping(path = "/branch", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class BranchController {
    private final BranchService branchService;

    @PostMapping("/list")
    public ResponseEntity<BranchListResponse> list() {
        log.info("returning_branch_list");
        var branches = branchService.listAll();
        var response = BranchListResponse.builder()
                .branches(branches)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/find-in-range")
    public ResponseEntity<BranchFindInRangeResponse> findInRange(
            @RequestData BranchFindInRangeRequest request) {
        log.info("returning_branch_list_in_range: " + request.toString());
        var branches = branchService.findInRange(
                request.getXLeft(),
                request.getXRight(),
                request.getYUp(),
                request.getYDown());
        var response = BranchFindInRangeResponse.builder()
                .branches(branches)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/get")
    public ResponseEntity<BranchGetResponse> get(
            @RequestData BranchGetRequest request) {
        log.info("fetching_batch: " + request.getId());
        try {
            var branch = branchService.get(request.getId());
            var response = BranchGetResponse.builder()
                    .branch(branch)
                    .build();
            return ResponseEntity.ok(response);
        } catch (BranchNotFoundException e) {
            log.error("branch_not_found: " + request.getId());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }
    }

}