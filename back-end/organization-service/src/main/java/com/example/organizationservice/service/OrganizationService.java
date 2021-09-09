package com.example.organizationservice.service;

import com.example.organizationservice.model.Organization;
import com.example.organizationservice.repository.OrganizationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    public Organization add(Organization organization) {
        organizationRepository.save(organization);
        return organization;
    }

    public Organization findById(Long id) {
        Optional<Organization> organization = organizationRepository.findById(id);
        return organization.orElse(null);
    }

    public List<Organization> findAll() {
        return organizationRepository.findAll();
    }

    @Transactional
    public Organization update(Long id, Organization organizationDetails) {
        Organization organization = organizationRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                "Organization with id " + id + " does not exist."
        ));

        organization.setName(organizationDetails.getName());
        organization.setAddress(organizationDetails.getAddress());

        Organization updatedOrganization = organizationRepository.save(organization);

        return  updatedOrganization;
    }

    public void deleteById(Long id) {
        boolean exists = organizationRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Organization with id " + id + " does not exist.");
        }
        organizationRepository.deleteById(id);
    }
}
