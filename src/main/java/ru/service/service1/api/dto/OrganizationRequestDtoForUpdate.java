package ru.service.service1.api.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.service.service1.db.data.Address;
import ru.service.service1.db.data.Coordinates;
import ru.service.service1.db.data.OrganizationType;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationRequestDtoForUpdate {

    @NotNull
    @XmlAttribute
    private Long id;

    @XmlAttribute
    private String name; //Поле не может быть null, Строка не может быть пустой

    @XmlElement
    private Coordinates coordinates; //Поле не может быть null

    @XmlAttribute
    private float annualTurnover; //Значение поля должно быть больше 0

    @XmlAttribute
    private long employeesCount; //Значение поля должно быть больше 0

    @XmlAttribute
    private OrganizationType type; //Поле может быть null

    @XmlElement
    private Address officialAddress; //Поле может быть null
}
