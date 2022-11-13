package ru.service.service1.api.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.service.service1.db.data.Address;
import ru.service.service1.db.data.Coordinates;
import ru.service.service1.db.data.OrganizationType;

import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationUpdateRq {

    @NotNull
//    @XmlAttribute
    private Long id;

    //    @XmlAttribute
    private String name; //Поле не может быть null, Строка не может быть пустой

    //    @XmlElement
    private Coordinates coordinates; //Поле не может быть null

    //    @XmlAttribute
    private float annualTurnover; //Значение поля должно быть больше 0

    //    @XmlAttribute
    private long employeesCount; //Значение поля должно быть больше 0

    //    @XmlAttribute
    private OrganizationType type; //Поле может быть null

    //    @XmlElement
    private Address officialAddress; //Поле может быть null
}
