package com.junior.university.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.junior.university.model.DepartmentEmployee;

@Repository
public interface DepartmentEmployeeRepository extends JpaRepository<DepartmentEmployee, Long> {

    Optional<DepartmentEmployee> findByDepartmentNameAndIsHeadOfDepartment(final String departmentName, final Boolean head);

    List<DepartmentEmployee> findAllByDepartmentName(final String departmentName);

    @Query("SELECT AVG(dep.employee.salary) FROM DepartmentEmployee dep WHERE department.name=:name")
    Long findAverageSalaryByDepartmentName(@Param("name") final String departmentName);

    Long countByDepartmentName(final String departmentName);
}
