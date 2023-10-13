package com.example.moretech5back.web.httpData.branch;

import com.example.moretech5back.model.Branch;
import com.example.moretech5back.model.User;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Data
@Builder
@Jacksonized
public class BranchListResponse {
    private List<Branch> branches;
}

