package com.junior.university.service;

import java.util.Map;

import com.junior.university.dto.EmployeeDTO;
import com.junior.university.model.Department;
import com.junior.university.model.Employee;

public interface DepartmentService {

    Department add(final Department department);

    Department addEmployee(final EmployeeDTO employeeDTO);

    Employee getHeadOfDepartment(final String departmentName);

    Map<String, Integer> getDepartmentStatistic(final String departmentName);

    Long getAverageSalaryByDepartmentName(final String departmentName);

    Long getDepartmentEmployeesNumber(final String departmentName);
}
