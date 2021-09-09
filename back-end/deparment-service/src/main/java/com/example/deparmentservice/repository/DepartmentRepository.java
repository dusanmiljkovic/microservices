package com.example.deparmentservice.repository;

import com.example.deparmentservice.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Department repository.
 */
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    /**
     * Find all departments by organization is.
     * @param organizationId Organization id.
     * @return List of departments.
     */
    List<Department> findDepartmentsByOrganizationId(Long organizationId);
}
