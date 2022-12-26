package ru.service.service1.api.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.service.service1.db.data.Organization;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeRs {

    Long id;

    String firstName;

    String lastName;

    Long organizationId;

}
