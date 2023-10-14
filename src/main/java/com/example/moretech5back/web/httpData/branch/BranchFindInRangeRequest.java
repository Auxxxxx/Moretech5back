package com.example.moretech5back.web.httpData.branch;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class BranchFindInRangeRequest {
    private Double xLeft;
    private Double yUp;
    private Double xRight;
    private Double yDown;

    public String toString() {
        return String.format("%f:%f %f:%f", xLeft, yUp, xRight, yDown);
    }
}