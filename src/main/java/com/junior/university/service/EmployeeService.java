package com.junior.university.service;

import java.util.List;

import com.junior.university.model.Employee;

public interface EmployeeService {

    List<Employee> search(final String search);
}
