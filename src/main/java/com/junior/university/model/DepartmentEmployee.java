package com.junior.university.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "department_employees")
public class DepartmentEmployee extends BaseEntity {

    @ManyToOne
    private Department department;

    @ManyToOne
    private Employee employee;

    @Column(name = "head_of_department")
    private Boolean isHeadOfDepartment;
}
