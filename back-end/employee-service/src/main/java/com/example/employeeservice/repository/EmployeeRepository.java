package com.example.employeeservice.repository;

import com.example.employeeservice.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * EMployee repository.
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    /**
     * Get all employees by department id.
     * @param departmentId Department id.
     * @return List of employees.
     */
    List<Employee> getEmployeesByDepartmentId(Long departmentId);

    /**
     * Get all employees by organization id.
     * @param organizationId Organization id.
     * @return List of employees.
     */
    List<Employee> getEmployeesByOrganizationId(Long organizationId);
}
