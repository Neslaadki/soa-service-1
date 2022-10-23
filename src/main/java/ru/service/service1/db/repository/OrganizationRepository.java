package ru.service.service1.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.service.service1.db.data.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

}
