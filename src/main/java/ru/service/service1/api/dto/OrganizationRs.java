package ru.service.service1.api.dto;

import lombok.*;
import ru.service.service1.db.data.Address;
import ru.service.service1.db.data.Coordinates;
import ru.service.service1.db.data.OrganizationType;

import java.time.LocalDateTime;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationRs {

    private Long id;

    private String name; //Поле не может быть null, Строка не может быть пустой

    private Coordinates coordinates; //Поле не может быть null

    private LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически

    private float annualTurnover; //Значение поля должно быть больше 0

    private long employeesCount; //Значение поля должно быть больше 0

    private OrganizationType type; //Поле может быть null

    private Address officialAddress; //Поле может быть null
}
