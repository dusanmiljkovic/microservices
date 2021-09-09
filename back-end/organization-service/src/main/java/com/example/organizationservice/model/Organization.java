package com.example.organizationservice.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Organization class.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Organization{
        /**
         * Organization id.
         */
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        /**
         * Organization name.
         */
        private String name;
        /**
         * Organization address.
         */
        private String address;
        /**
         * List of departments.
         */
        @Transient
        private List<Department> departments= new ArrayList<>();;
        /**
         * List of employees.
         */
        @Transient
        private List<Employee> employees = new ArrayList<>();
}
