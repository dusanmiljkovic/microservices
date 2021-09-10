package com.example.employeeservice.controller;

import com.example.employeeservice.model.Employee;
import com.example.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Empployee controller.
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@AllArgsConstructor
public class EmployeeController {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);
    /**
     * Employee service.
     */
    private final EmployeeService employeeService;

    /**
     * Add employee.
     * @param employee Employee.
     * @return Added employee.
     */
    @PostMapping("/")
    public Employee add(@RequestBody Employee employee) {
        LOGGER.info("Employee add: {}", employee);
        return employeeService.add(employee);
    }

    /**
     * Get employee by id.
     * @param id Employee id.
     * @return Employee.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Employee>  findById(@PathVariable("id") Long id) {
        LOGGER.info("Employee find: id={}", id);
        return employeeService.findById(id);
    }

    /**
     * Uodate employee.
     * @param id Employee id.
     * @param employee Employee details.
     * @return Updated employee.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable("id") Long id, @RequestBody Employee employee) {
        LOGGER.info("Update employee: id={}", id);
        return employeeService.update(id, employee);
    }

    /**
     * Delete employee.
     * @param id Employee id.
     * @return Response entity.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteById(@PathVariable("id") Long id) {
        LOGGER.info("Employee find: id={}", id);
        return employeeService.deleteById(id);
    }

    /**
     * Delete employees by department id.
     * @param id Department id.
     * @return Response.
     */
    @DeleteMapping("/department/{departmentId}")
    public ResponseEntity<Map<String, Boolean>> deleteByDepartmentId(@PathVariable("departmentId") Long id) {
        LOGGER.info("Employee find: id={}", id);
        return employeeService.deleteByDepartmentId(id);
    }

    /**
     * Detelete employees by organization id.
     * @param organizationId Organization id.
     * @return Response entity.
     */
    @DeleteMapping("/organization/{organizationId}")
    public ResponseEntity<Map<String, Boolean>> deleteByOrganizationId(@PathVariable("organizationId") Long organizationId) {
        LOGGER.info("Employee find: id={}", organizationId);
        return employeeService.deleteByOrganizationId(organizationId);
    }

    /**
     * Get all employees.
     * @return List of employees.
     */
    @GetMapping("/")
    public List<Employee> findAll() {
        LOGGER.info("Employee find");
        return employeeService.findAll();
    }

    /**
     * Find all employees by department id.
     * @param departmentId Department id.
     * @return List of employees.
     */
    @GetMapping("/department/{departmentId}")
    public List<Employee> findByDepartment(@PathVariable("departmentId") Long departmentId) {
        LOGGER.info("Employee find: departmentId={}", departmentId);
        return employeeService.findByDepartment(departmentId);
    }

    /**
     * Get all employees by organization id.
     * @param organizationId Organization id.
     * @return List of employees.
     */
    @GetMapping("/organization/{organizationId}")
    public List<Employee> findByOrganization(@PathVariable("organizationId") Long organizationId) {
        LOGGER.info("Employee find: organizationId={}", organizationId);
        return employeeService.findByOrganization(organizationId);
    }
}
