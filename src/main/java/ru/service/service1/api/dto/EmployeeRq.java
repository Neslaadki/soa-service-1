package ru.service.service1.api.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRq {

    @NotNull String firstName;

    @NotNull String lastName;

    @NotNull Date birthday;

}
