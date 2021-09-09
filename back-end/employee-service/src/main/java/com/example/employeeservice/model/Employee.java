package com.example.employeeservice.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Employee class.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Employee {
    /**
     * Employee id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * Organization id.
     */
    private Long organizationId;
    /**
     * Department id.
     */
    private Long departmentId;
    /**
     * Employee name.
     */
    private String name;
    /**
     * Employee position.
     */
    private String position;
    /**
     * Employee date of birth.
     */
    private LocalDate dob;
}
