package com.example.moretech5back.web.httpData.employee;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;
import online.mdfactory.backend.model.Employee;
import online.mdfactory.backend.model.OperationGroup;

import java.util.List;

@Data
@Builder
@Jacksonized
public class EmployeeGetResponse {
    private Employee employee;
    private List<OperationGroup> operationGroups;
}