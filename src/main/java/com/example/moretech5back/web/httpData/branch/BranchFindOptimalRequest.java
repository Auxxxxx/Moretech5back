package com.example.moretech5back.web.httpData.branch;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class BranchFindOptimalRequest {
    private Double xMe;
    private Double yMe;

    public String toString() {
        return String.format("%f:%f", xMe, yMe);
    }
}