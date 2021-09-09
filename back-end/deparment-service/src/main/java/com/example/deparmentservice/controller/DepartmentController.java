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

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@AllArgsConstructor
public class DepartmentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    public final DepartmentService departmentService;

    @Autowired
    EmployeeClient employeeClient;

    @PostMapping("/")
    public Department add(@RequestBody Department department) {
        LOGGER.info("Department add: {}", department);
        return departmentService.add(department);
    }

    @GetMapping("/{id}")
    public Department findById(@PathVariable("id") Long id) {
        LOGGER.info("Department find: id={}", id);
        return departmentService.findById(id);
    }

    @GetMapping("/")
    public List<Department> findAll() {
        LOGGER.info("Department find");
        return departmentService.findAll();
    }

    @PutMapping("/{id}")
    public Department update(@PathVariable("id") Long id, @RequestBody Department department) {
        LOGGER.info("Update organization: id={}", id);
        return departmentService.update(id, department);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        LOGGER.info("Employee find: id={}", id);
        departmentService.deleteById(id);
        employeeClient.deleteByDepartment(id);
    }

    @DeleteMapping("/organization/{organizationId}")
    public void deleteByOrganizationId(@PathVariable("organizationId") Long id) {
        LOGGER.info("Employee find: id={}", id);
        employeeClient.deleteByOrganization(id);
        departmentService.deleteByOrganizationId(id);
    }

    @GetMapping("/organization/{organizationId}")
    public List<Department> findByOrganization(@PathVariable("organizationId") Long organizationId) {
        LOGGER.info("Department find: organizationId={}", organizationId);
        return departmentService.findByOrganization(organizationId);
    }

    @GetMapping("/{id}/with-employees")
    public Department findByIdWithEmployees(@PathVariable("id") Long id) {
        LOGGER.info("Department find: id={}", id);
        Department department = departmentService.findById(id);
        department.setEmployees(employeeClient.findByDepartment(id));
        return department;
    }

    @GetMapping("/organization/{organizationId}/with-employees")
    public List<Department> findByOrganizationWithEmployees(@PathVariable("organizationId") Long organizationId) {
        LOGGER.info("Department find: organizationId={}", organizationId);
        List<Department> departments = departmentService.findByOrganization(organizationId);
        departments.forEach(d -> d.setEmployees(employeeClient.findByDepartment(d.getId())));
        return departments;
    }
}
