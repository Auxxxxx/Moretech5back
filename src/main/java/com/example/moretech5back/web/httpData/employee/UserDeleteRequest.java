package online.mdfactory.backend.web.httpData.employee;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class EmployeeDeleteRequest {
    private String login;
}