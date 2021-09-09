package com.example.deparmentservice.controller;

import com.example.deparmentservice.client.EmployeeClient;
import com.example.deparmentservice.model.Department;
import com.example.deparmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Department controller.
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@AllArgsConstructor
public class DepartmentController {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    /**
     * Department service.
     */
    public final DepartmentService departmentService;

    /**
     * Employee client.
     */
    @Autowired
    EmployeeClient employeeClient;

    /**
     * Create department.
     * @param department Department,
     * @return Created department.
     */
    @PostMapping("/")
    public Department add(@RequestBody Department department) {
        LOGGER.info("Department add: {}", department);
        return departmentService.add(department);
    }

    /**
     * Get department.
     * @param id Department id.
     * @return Department.
     */
    @GetMapping("/{id}")
    public Department findById(@PathVariable("id") Long id) {
        LOGGER.info("Department find: id={}", id);
        return departmentService.findById(id);
    }

    /**
     * Get all departments.
     * @return List of departments.
     */
    @GetMapping("/")
    public List<Department> findAll() {
        LOGGER.info("Department find");
        return departmentService.findAll();
    }

    /**
     * Update department.
     * @param id Department id.
     * @param department Department details.
     * @return Updated department.
     */
    @PutMapping("/{id}")
    public Department update(@PathVariable("id") Long id, @RequestBody Department department) {
        LOGGER.info("Update organization: id={}", id);
        return departmentService.update(id, department);
    }

    /**
     * Delete department by id.
     * @param id Department id.
     */
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        LOGGER.info("Employee find: id={}", id);
        departmentService.deleteById(id);
        employeeClient.deleteByDepartment(id);
    }

    /**
     * Delete departments by organization id.
     * @param id Organization id.
     */
    @DeleteMapping("/organization/{organizationId}")
    public void deleteByOrganizationId(@PathVariable("organizationId") Long id) {
        LOGGER.info("Employee find: id={}", id);
        employeeClient.deleteByOrganization(id);
        departmentService.deleteByOrganizationId(id);
    }

    /**
     * Get departments bby organization id.
     * @param organizationId Organization id.
     * @return List of departments.
     */
    @GetMapping("/organization/{organizationId}")
    public List<Department> findByOrganization(@PathVariable("organizationId") Long organizationId) {
        LOGGER.info("Department find: organizationId={}", organizationId);
        return departmentService.findByOrganization(organizationId);
    }

    /**
     * Get department with employees.
     * @param id Department id.
     * @return Department.
     */
    @GetMapping("/{id}/with-employees")
    public Department findByIdWithEmployees(@PathVariable("id") Long id) {
        LOGGER.info("Department find: id={}", id);
        Department department = departmentService.findById(id);
        department.setEmployees(employeeClient.findByDepartment(id));
        return department;
    }

    /**
     * Get departments with employees by organization id.
     * @param organizationId Organization id.
     * @return Departments.
     */
    @GetMapping("/organization/{organizationId}/with-employees")
    public List<Department> findByOrganizationWithEmployees(@PathVariable("organizationId") Long organizationId) {
        LOGGER.info("Department find: organizationId={}", organizationId);
        List<Department> departments = departmentService.findByOrganization(organizationId);
        departments.forEach(d -> d.setEmployees(employeeClient.findByDepartment(d.getId())));
        return departments;
    }
}
