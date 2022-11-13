package ru.service.service1.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.service.service1.db.data.Address;
import ru.service.service1.db.data.Coordinates;
import ru.service.service1.db.data.OrganizationType;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationRq {

    @NotBlank
    private String name; //Поле не может быть null, Строка не может быть пустой

    @NotNull
    private Coordinates coordinates; //Поле не может быть null

    @Min(0)
    private float annualTurnover; //Значение поля должно быть больше 0

    @NotNull
    private OrganizationType type; //Поле может быть null

    @NotNull
    private Address officialAddress; //Поле может быть null

}
