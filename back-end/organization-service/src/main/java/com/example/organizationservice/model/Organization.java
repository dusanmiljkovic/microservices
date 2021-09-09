package com.example.organizationservice.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Organization{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;
        private String address;
        @CreatedDate
        Date createdAt;

        @Transient
        private List<Department> departments= new ArrayList<>();;

        @Transient
        private List<Employee> employees = new ArrayList<>();
}
