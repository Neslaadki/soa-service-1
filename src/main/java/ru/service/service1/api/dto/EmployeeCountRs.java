package ru.service.service1.api.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.service.service1.db.data.OrganizationType;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeCountRs {

    private OrganizationType organizationType;

    private long employeeCount;

}
