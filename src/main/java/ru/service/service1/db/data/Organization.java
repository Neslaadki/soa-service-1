package ru.service.service1.db.data;


import java.time.ZonedDateTime;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class Organization {

    @Id
    Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически

    @NotBlank
    String name; //Поле не может быть null, Строка не может быть пустой

    @NotNull
    @Embedded
    Coordinates coordinates; //Поле не может быть null

    @NotNull
    @CreatedDate
    ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически

    @Min(0)
    float annualTurnover; //Значение поля должно быть больше 0

    @Min(0)
    long employeesCount; //Значение поля должно быть больше 0

    @NotNull
    OrganizationType type; //Поле может быть null

    @NotNull
    @Embedded
    Address officialAddress; //Поле может быть null

}
