package ru.service.service1.impl.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.service.service1.api.dto.OrganizationRq;
import ru.service.service1.api.dto.OrganizationUpdateRq;
import ru.service.service1.api.dto.OrganizationRs;
import ru.service.service1.db.data.Organization;
import ru.service.service1.db.data.OrganizationType;
import ru.service.service1.db.repository.OrganizationRepository;
import ru.service.service1.impl.exception.DeleteException;
import ru.service.service1.impl.exception.NotFoundException;
import ru.service.service1.impl.mapper.OrganizationMapper;
import ru.service.service1.impl.service.OrganizationService;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;

    private final OrganizationMapper organizationMapper;

    @Override
    public OrganizationRs findById(Long id) {

        Optional<Organization> optionalOrganization = organizationRepository.findById(id);

        if (optionalOrganization.isPresent()) {

            log.info("findById return : {}", optionalOrganization.get());
            return organizationMapper.toOrganizationResponseDto(optionalOrganization.get());
        } else {

            log.info("findById throw NotFoundException for id : {}", id);
            throw new NotFoundException(Organization.class, id);
        }

        // попоробовать красивее
    }

    @Override
    public Page<OrganizationRs> findAll(Pageable pageable) {

        Page<Organization> organizationList = organizationRepository.findAll(pageable);

        log.info("findAll return page of : {}", organizationList);
        return new PageImpl<>(
                organizationList.stream().map(organizationMapper::toOrganizationResponseDto).toList());
    }

    @Override
    @Transactional
    public OrganizationRs deleteById(Long id) {

        OrganizationRs organizationRs = findById(id);
        organizationRepository.deleteById(id);

        try {
            findById(id);

            log.warn("deleteById can't delete organization with id: {}", id);
            throw new DeleteException(Organization.class, id);
        } catch (NotFoundException e) {

            log.info("deleteById return dto: {}", organizationRs);
            return organizationRs;
        }
    }

    @Override
    @Transactional
    public OrganizationRs create(OrganizationRq organizationRq) {

        Organization organization = organizationMapper.toOrganization(organizationRq);
        Organization saved = organizationRepository.save(organization);

        log.info("create return organization with id : {}", organization.getId());
        return findById(saved.getId());
    }

    @Override
    @Transactional
    public List<OrganizationRs> deleteWithType(OrganizationType organizationType) {

        List<Organization> organizationList = organizationRepository.findByType(organizationType);
        List<Long> ids = organizationList.stream().map(Organization::getId).toList();

        organizationRepository.deleteAllById(ids);

        for (Long id : ids) {
            try {
                findById(id);

                log.warn("deleteWithType can't delete organization with id : {}", id);
                throw new DeleteException(Organization.class, id);
            } catch (NotFoundException ignored) {}
        }

        log.info("deleteWithType return : {}", organizationList);
        return organizationList.stream().map(organizationMapper::toOrganizationResponseDto).toList();
    }

    @Override
    public List<OrganizationRs> findByEmployeeCountBiggestThan(long count) {

        List<Organization> organizationList =
                organizationRepository.findAll().stream()
                        .filter(o -> o.getEmployeesCount() > count)
                        .toList();

        log.info("findByEmployeeCountBiggestThan return count = {}", organizationList.size());
        return organizationList.stream().map(organizationMapper::toOrganizationResponseDto).toList();
    }

    @Override
    public Map<OrganizationType, Long> getMapWithOrganizationTypeAndCountOfOrganizations() {

        List<Organization> organizationList = organizationRepository.findAll();
        Map<OrganizationType, Long> map =
                organizationList.stream()
                        .collect(Collectors.groupingBy(Organization::getType, Collectors.counting()));

        log.info("getMapWithOrganizationTypeAndCountOfOrganizations return : {}", map.toString());
        return map;
    }

    @Override
    public OrganizationRs updateById(
            OrganizationUpdateRq organizationUpdateRq) {

        // todo

        return null;
    }
}
