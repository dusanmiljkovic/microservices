package com.example.organizationservice.controller;

import com.example.organizationservice.client.DepartmentClient;
import com.example.organizationservice.client.EmployeeClient;
import com.example.organizationservice.model.Organization;
import com.example.organizationservice.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@AllArgsConstructor
public class OrganizationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationController.class);

    public final OrganizationService organizationService;
    public final DepartmentClient departmentClient;
    public final EmployeeClient employeeClient;

    @PostMapping
    public Organization add(@RequestBody Organization organization) {
        LOGGER.info("Organization add: {}", organization);
        return organizationService.add(organization);
    }

    @GetMapping
    public List<Organization> findAll() {
        LOGGER.info("Organization find");
        return organizationService.findAll();
    }

    @GetMapping("/{id}")
    public Organization findById(@PathVariable("id") Long id) {
        LOGGER.info("Organization find: id={}", id);
        return organizationService.findById(id);
    }

    @PutMapping("/{id}")
    public Organization update(@PathVariable("id") Long id, @RequestBody Organization organization) {
        LOGGER.info("Update organization: id={}", id);
        return organizationService.update(id, organization);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        LOGGER.info("Employee find: id={}", id);
        organizationService.deleteById(id);
        departmentClient.deleteByOrganization(id);
    }

    @GetMapping("/{id}/with-departments")
    public Organization findByIdWithDepartments(@PathVariable("id") Long id) {
        LOGGER.info("Organization find: id={}", id);
        Organization organization = organizationService.findById(id);
        organization.setDepartments(departmentClient.findByOrganization(organization.getId()));
        return organization;
    }

    @GetMapping("/{id}/with-departments-and-employees")
    public Organization findByIdWithDepartmentsAndEmployees(@PathVariable("id") Long id) {
        LOGGER.info("Organization find: id={}", id);
        Organization organization = organizationService.findById(id);
        organization.setDepartments(departmentClient.findByOrganizationWithEmployees(organization.getId()));
        return organization;
    }

    @GetMapping("/{id}/with-employees")
    public Organization findByIdWithEmployees(@PathVariable("id") Long id) {
        LOGGER.info("Organization find: id={}", id);
        Organization organization = organizationService.findById(id);
        organization.setEmployees(employeeClient.findByOrganization(organization.getId()));
        return organization;
    }

}
