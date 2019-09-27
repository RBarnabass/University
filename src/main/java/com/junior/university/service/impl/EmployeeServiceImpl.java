package com.junior.university.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.junior.university.model.Employee;
import com.junior.university.repository.EmployeeRepository;
import com.junior.university.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(final EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> search(final String sear) {

        return employeeRepository.findAll(EmployeeRepository.Specifications.search(sear));
    }
}
