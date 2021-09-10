package com.example.employeeservice.service;

import com.example.employeeservice.model.Employee;
import com.example.employeeservice.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Employee service.
 */
@Service
@AllArgsConstructor
public class EmployeeService {

    /**
     * Instance od employee repository.
     */
    private final EmployeeRepository employeeRepository;

    /**
     * Add employee.
     * @param employee Employee.
     * @return Added employee.
     */
    public Employee add(Employee employee) {
        employeeRepository.save(employee);
        return employee;
    }

    /**
     * Get employee by id.
     * @param id Employee id.
     * @return Employee.
     */
    public ResponseEntity<Employee> findById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                "Employee with id " + id + " does not exist."
        ));
        return ResponseEntity.ok(employee);
    }

    /**
     * Get all employees.
     * @return List of employees.
     */
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    /**
     * Find all employees by department id.
     * @param departmentId Department id.
     * @return List of employees.
     */
    public List<Employee> findByDepartment(Long departmentId) {
        return employeeRepository.getEmployeesByDepartmentId(departmentId);
    }

    /**
     * Get all employees by organization id.
     * @param organizationId Organization id.
     * @return List of employees.
     */
    public List<Employee> findByOrganization(Long organizationId) {
        return employeeRepository.getEmployeesByOrganizationId(organizationId);
    }

    /**
     * Delete employee.
     * @param id Employee id.
     * @return Response entity.
     */
    public ResponseEntity<Map<String, Boolean>> deleteById(Long id) {
        boolean exists = employeeRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Employee with id " + id + " does not exist.");
        }
        employeeRepository.deleteById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    /**
     * Delete employees by department id.
     * @param id Department id.
     * @return Response.
     */
    public ResponseEntity<Map<String, Boolean>> deleteByDepartmentId(Long id) {
        List<Employee> employees = employeeRepository.getEmployeesByDepartmentId(id);
        employees.forEach( employee -> {employeeRepository.deleteById(employee.getId());});
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }


    /**
     * Detelete employees by organization id.
     * @param organizationId Organization id.
     * @return Response entity.
     */
    public ResponseEntity<Map<String, Boolean>> deleteByOrganizationId(Long id) {
        List<Employee> employees = employeeRepository.getEmployeesByOrganizationId(id);
        employees.forEach( employee -> {employeeRepository.deleteById(employee.getId());});
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    /**
     * Uodate employee.
     * @param id Employee id.
     * @param employeeDetails Employee details.
     * @return Updated employee.
     */
    @Transactional
    public ResponseEntity<Employee> update(Long id, Employee employeeDetails) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                "Employee with id " + id + " does not exist."
        ));

        employee.setName(employeeDetails.getName());
        employee.setPosition(employeeDetails.getPosition());
        employee.setDob(employeeDetails.getDob());

        Employee updatedEmployee = employeeRepository.save(employee);

        return ResponseEntity.ok(updatedEmployee);
    }
}
