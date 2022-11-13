package ru.service.service1.db.data;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.print.attribute.standard.MediaSize;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @Column(nullable = false, name = "first_name")
    String firstName;

    @Column(nullable = false, name = "last_name")
    String lastName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn( nullable = false, name = "organization_id")
    Organization organization;

}
