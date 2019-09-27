package com.junior.university.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String degree;

    @NotNull
    private BigDecimal salary;

    @NotNull
    private Long departmentId;

    private Boolean isHead;
}
