package com.example.deparmentservice.service;

import com.example.deparmentservice.model.Department;
import com.example.deparmentservice.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Department service.
 */
@Service
@Slf4j
@AllArgsConstructor
public class DepartmentService {
    /**
     * Department repository.
     */
    private final DepartmentRepository departmentRepository;

    /**
     * Add department.
     * @param department Department.
     * @return Saved department.
     */
    public Department add(Department department) {
        departmentRepository.save(department);
        return department;
    }

    /**
     * Find Department by Id.
     * @param id Department id.
     * @return Department.
     */
    public Department findById(Long id) {
        Optional<Department> organization = departmentRepository.findById(id);
        return organization.orElse(null);
    }

    /**
     * Find all departments.
     * @return List of departments.
     */
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    /**
     * Find all departments by organization id.
     * @param organizationId Organization id.
     * @return List od departments.
     */
    public List<Department> findByOrganization(Long organizationId) {
        return departmentRepository.findDepartmentsByOrganizationId(organizationId);
    }

    /**
     * Update department.
     * @param id Department id.
     * @param departmentDetails Department details.
     * @return Updated department.
     */
    @Transactional
    public Department update(Long id, Department departmentDetails) {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                "Department with id " + id + " does not exist."
        ));
        department.setName(departmentDetails.getName());
        Department updatedDepartment = departmentRepository.save(department);
        return  updatedDepartment;
    }

    /**
     * Delete department by id.
     * @param id Department id.
     */
    public void deleteById(Long id) {
        boolean exists = departmentRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Department with id " + id + " does not exist.");
        }
        departmentRepository.deleteById(id);
    }

    /**
     * Delete departments by organization id.
     * @param id Organization id.
     */
    public void deleteByOrganizationId(Long id) {
        List<Department> departments = departmentRepository.findDepartmentsByOrganizationId(id);
        departments.forEach(department -> {departmentRepository.deleteById(department.getId());});
    }
}
