package ru.service.service1.api.dto;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import javax.persistence.Embedded;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import ru.service.service1.db.data.Address;
import ru.service.service1.db.data.Coordinates;
import ru.service.service1.db.data.OrganizationType;


@Getter
@Setter
@Builder
//@XmlRootElement
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationResponseDto {

    private Long id;

    @NotBlank
    private String name; //Поле не может быть null, Строка не может быть пустой

    @NotNull
    private Coordinates coordinates; //Поле не может быть null

    @NotNull
    @CreatedDate
    private LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически

    @Min(0)
    private float annualTurnover; //Значение поля должно быть больше 0

    @Min(0)
    private long employeesCount; //Значение поля должно быть больше 0

    @NotNull
    private OrganizationType type; //Поле может быть null

    @NotNull
    private Address officialAddress; //Поле может быть null
}
