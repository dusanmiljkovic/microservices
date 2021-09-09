package com.example.deparmentservice.client;

import com.example.deparmentservice.model.Employee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Employee client.
 */
@FeignClient(name = "EMPLOYEE-SERVICE")
public interface EmployeeClient {

    /**
     * Find all employees by department id.
     * @param departmentId department id.
     * @return list od employees.
     */
    @GetMapping("/department/{departmentId}")
    List<Employee> findByDepartment(@PathVariable("departmentId") Long departmentId);

    /**
     * Delete employees by department id.
     * @param departmentId department id.
     */
    @DeleteMapping("/department/{departmentId}")
    void deleteByDepartment(@PathVariable("departmentId") Long departmentId);

    /**
     * Delete employees by organization id.
     * @param organizationId organization id.
     */
    @DeleteMapping("/organization/{organizationId}")
    void deleteByOrganization(@PathVariable("organizationId") Long organizationId);
}
