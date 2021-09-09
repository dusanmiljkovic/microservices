package com.example.organizationservice.model;

import lombok.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Department {
    private Long id;
    private Long organizationId;
    private String name;
    Date createdAt;
    private List<Employee> employees = new ArrayList<>();
}
