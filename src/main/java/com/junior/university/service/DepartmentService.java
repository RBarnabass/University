package com.junior.university.service;

import com.junior.university.model.Department;
import com.junior.university.model.Employee;

public interface DepartmentService {

    Department add(final Department department);

    Employee getHeadOfDepartment(final String departmentName);
}
