package ru.service.service1.db.data;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @NotBlank String name;

    @NotNull
    @Embedded
    Coordinates coordinates;

    @NotNull
    @CreatedDate
    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    LocalDateTime creationDate;

    @Min(0)
    float annualTurnover;

    @Min(0)
    long employeesCount;

    @NotNull OrganizationType type;

    @NotNull
    @Embedded
    Address officialAddress;
}
