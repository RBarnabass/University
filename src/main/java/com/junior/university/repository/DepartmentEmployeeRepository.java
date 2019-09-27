package com.junior.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.junior.university.model.DepartmentEmployee;

@Repository
public interface DepartmentEmployeeRepository extends JpaRepository<DepartmentEmployee, Long> {

    DepartmentEmployee findByDepartmentNameAndIsHeadOfDepartment(final String departmentName, final Boolean head);
}
