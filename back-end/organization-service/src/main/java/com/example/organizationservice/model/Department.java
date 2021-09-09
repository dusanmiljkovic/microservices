package com.example.organizationservice.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Department class.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Department {
    private Long id;
    /**
     * Organization id.
     */
    private Long organizationId;
    /**
     * Name.
     */
    private String name;
    /**
     * List of employees.
     */
    private List<Employee> employees = new ArrayList<>();
}
