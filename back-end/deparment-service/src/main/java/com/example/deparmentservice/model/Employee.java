package com.example.deparmentservice.model;

import lombok.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Employee {
    private Long id;
    private Long organizationId;
    private Long departmentId;
    private String name;
    private String position;
    private LocalDate dob;
    Date createdAt;
    private Integer age;
    public Integer getAge() {
        return Period.between(this. dob, LocalDate.now()).getYears();
    }
}
