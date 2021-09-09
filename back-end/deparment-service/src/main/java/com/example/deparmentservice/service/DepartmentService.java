package com.example.deparmentservice.service;

import com.example.deparmentservice.model.Department;
import com.example.deparmentservice.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public Department add(Department department) {
        departmentRepository.save(department);
        return department;
    }

    public Department findById(Long id) {
        Optional<Department> organization = departmentRepository.findById(id);
        return organization.orElse(null);
    }

    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    public List<Department> findByOrganization(Long organizationId) {
        return departmentRepository.findDepartmentsByOrganizationId(organizationId);
    }

    @Transactional
    public Department update(Long id, Department departmentDetails) {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                "Department with id " + id + " does not exist."
        ));

        department.setName(departmentDetails.getName());

        Department updatedDepartment = departmentRepository.save(department);

        return  updatedDepartment;
    }

    public void deleteById(Long id) {
        boolean exists = departmentRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Department with id " + id + " does not exist.");
        }
        departmentRepository.deleteById(id);
    }

    public void deleteByOrganizationId(Long id) {
        List<Department> departments = departmentRepository.findDepartmentsByOrganizationId(id);
        departments.forEach(department -> {departmentRepository.deleteById(department.getId());});
    }
}
