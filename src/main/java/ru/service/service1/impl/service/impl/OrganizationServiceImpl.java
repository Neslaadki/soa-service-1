package ru.service.service1.impl.service.impl;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.service.service1.api.dto.OrganizationRequestDto;
import ru.service.service1.api.dto.OrganizationResponseDto;
import ru.service.service1.db.data.Organization;
import ru.service.service1.db.data.OrganizationType;
import ru.service.service1.db.repository.OrganizationRepository;
import ru.service.service1.impl.service.OrganizationService;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;

    @Override
    public OrganizationResponseDto findById(Long id) {

        Optional<Organization> organization = organizationRepository.findById(id);

        return null;
    }

    @Override
    public Page<OrganizationResponseDto> findAll(Pageable pageable) {
        Page<Organization> organizationList = organizationRepository.findAll(pageable);
        return null;
    }

    @Override
    public OrganizationResponseDto deleteById(Long id) {
        return null;
    }

    @Override
    public OrganizationResponseDto create(OrganizationRequestDto organizationRequestDto) {
        return null;
    }

    @Override
    public List<OrganizationResponseDto> findByType(OrganizationType organizationType) {
        return null;
    }

    @Override
    public List<OrganizationResponseDto> findByEmployeeCountBiggestThan(long count) {
        return null;
    }
}
