package com.example.deparmentservice.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

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
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long organizationId;
    private String name;
    @CreatedDate
    Date createdAt;

    @Transient
    private List<Employee> employees = new ArrayList<>();
}
