package com.example.deparmentservice.client;

import com.example.deparmentservice.model.Employee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "EMPLOYEE-SERVICE")
public interface EmployeeClient {

    @GetMapping("/department/{departmentId}")
    List<Employee> findByDepartment(@PathVariable("departmentId") Long departmentId);

    @DeleteMapping("/department/{departmentId}")
    void deleteByDepartment(@PathVariable("departmentId") Long departmentId);

    @DeleteMapping("/organization/{organizationId}")
    void deleteByOrganization(@PathVariable("organizationId") Long organizationId);
}
