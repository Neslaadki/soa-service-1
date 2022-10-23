package ru.service.service1.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.service.service1.db.data.Organization;
import ru.service.service1.db.data.OrganizationType;

import java.util.List;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    List<Organization> findByType(OrganizationType organizationType);

}
