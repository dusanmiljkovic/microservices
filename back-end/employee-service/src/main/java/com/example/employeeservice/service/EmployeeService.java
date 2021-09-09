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
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Employee add(Employee employee) {
        employeeRepository.save(employee);
        return employee;
    }

    public ResponseEntity<Employee> findById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                "Employee with id " + id + " does not exist."
        ));
        return ResponseEntity.ok(employee);
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public List<Employee> findByDepartment(Long departmentId) {
        return employeeRepository.getEmployeesByDepartmentId(departmentId);
    }

    public List<Employee> findByOrganization(Long organizationId) {
        return employeeRepository.getEmployeesByOrganizationId(organizationId);
    }

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

    public ResponseEntity<Map<String, Boolean>> deleteByDepartmentId(Long id) {
        List<Employee> employees = employeeRepository.getEmployeesByDepartmentId(id);
        employees.forEach( employee -> {employeeRepository.deleteById(employee.getId());});
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }


    public ResponseEntity<Map<String, Boolean>> deleteByOrganizationId(Long id) {
        List<Employee> employees = employeeRepository.getEmployeesByOrganizationId(id);
        employees.forEach( employee -> {employeeRepository.deleteById(employee.getId());});
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

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
