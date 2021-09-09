package com.example.organizationservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
