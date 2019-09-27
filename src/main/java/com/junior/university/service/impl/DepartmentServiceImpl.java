package com.junior.university.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.junior.university.model.Department;
import com.junior.university.model.DepartmentEmployee;
import com.junior.university.model.Employee;
import com.junior.university.repository.DepartmentEmployeeRepository;
import com.junior.university.repository.DepartmentRepository;
import com.junior.university.service.DepartmentService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentEmployeeRepository departmentEmployeeRepository;

    @Autowired
    public DepartmentServiceImpl(final DepartmentRepository departmentRepository,
                                 final DepartmentEmployeeRepository departmentEmployeeRepository) {
        this.departmentRepository = departmentRepository;
        this.departmentEmployeeRepository = departmentEmployeeRepository;
    }

    @Override
    public Department add(final Department department) {
        
        log.info("Save department - {}", department);
        return departmentRepository.save(department);
    }

    @Override
    public Employee getHeadOfDepartment(final String departmentName) {
        final DepartmentEmployee departmentEmployee =
                departmentEmployeeRepository.findByDepartmentNameAndIsHeadOfDepartment(departmentName, true);

        return departmentEmployee.getEmployee();
    }
}
