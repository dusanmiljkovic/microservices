package com.example.employeeservice.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long organizationId;
    private Long departmentId;
    private String name;
    private String position;
    private LocalDate dob;
    @CreatedDate
    Date createdAt;

    @Transient
    private Integer age;

    public Integer getAge() {
        return Period.between(this. dob, LocalDate.now()).getYears();
    }
}
