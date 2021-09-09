package com.example.organizationservice.model;

import lombok.*;
import java.time.LocalDate;

/**
 * Employee class.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Employee {
    /**
     * Employee id.
     */
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

