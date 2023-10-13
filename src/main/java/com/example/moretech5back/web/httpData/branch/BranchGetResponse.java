package com.example.moretech5back.web.httpData.branch;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.awt.*;

@Data
@Builder
@Jacksonized
public class BranchGetResponse {
    private Long id;
    private String name;
    private String address;
    private Long load;
    private Point coordinates;
}