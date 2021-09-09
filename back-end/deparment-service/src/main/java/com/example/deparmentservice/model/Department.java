package com.example.deparmentservice.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Department class.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Department {
    /**
     * Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    @Transient
    private List<Employee> employees = new ArrayList<>();
}
