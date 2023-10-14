package com.example.moretech5back.web.httpData.branch;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class BranchGetRequest {
    private Long id;
}