package com.junior.university.service.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.junior.university.dto.EmployeeDTO;
import com.junior.university.model.Degree;
import com.junior.university.model.Department;
import com.junior.university.model.DepartmentEmployee;
import com.junior.university.model.Employee;
import com.junior.university.repository.DegreeRepository;
import com.junior.university.repository.DepartmentEmployeeRepository;
import com.junior.university.repository.DepartmentRepository;
import com.junior.university.service.DepartmentService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class DepartmentServiceImpl implements DepartmentService {

    private static final int INCREMENT_CONSTANT = 1;

    private final DegreeRepository degreeRepository;
    private final DepartmentRepository departmentRepository;
    private final DepartmentEmployeeRepository departmentEmployeeRepository;

    @Autowired
    public DepartmentServiceImpl(final DegreeRepository degreeRepository,
                                 final DepartmentRepository departmentRepository,
                                 final DepartmentEmployeeRepository departmentEmployeeRepository) {

        this.degreeRepository = degreeRepository;
        this.departmentRepository = departmentRepository;
        this.departmentEmployeeRepository = departmentEmployeeRepository;
    }

    @Override
    public Department add(final Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department addEmployee(final EmployeeDTO employeeDTO) {
        final Department department = departmentRepository.findById(employeeDTO.getDepartmentId()).orElseThrow();
        final Degree degree = degreeRepository.findByName(employeeDTO.getDegree()).orElseThrow();

        final Employee employee = buildEmployee(employeeDTO, degree);
        final DepartmentEmployee departmentEmployee = buildDepartmentEmployee(department, employee, employeeDTO.getIsHead());

        department.getDepartmentEmployees().add(departmentEmployee);
        departmentRepository.save(department);
        return department;
    }

    @Override
    public Employee getHeadOfDepartment(final String departmentName) {
        final DepartmentEmployee departmentEmployee =
                departmentEmployeeRepository.findByDepartmentNameAndIsHeadOfDepartment(departmentName, true);

        return departmentEmployee.getEmployee();
    }

    @Override
    public Map<String, Integer> getDepartmentStatistic(final String departmentName) {
        final List<DepartmentEmployee> allByDepartmentName =
                departmentEmployeeRepository.findAllByDepartmentName(departmentName);

        final Map<String, Integer> map = new HashMap<>();

        allByDepartmentName.forEach(departmentEmployee -> {
            final String name = departmentEmployee.getEmployee().getDegree().getName();
            map.put(name,
                    map.containsKey(name)
                            ? map.get(name) + INCREMENT_CONSTANT
                            : INCREMENT_CONSTANT);
        });

        return map;
    }

    @Override
    public Long getAverageSalaryByDepartmentName(final String departmentName) {
        return departmentEmployeeRepository.findAverageSalaryByDepartmentName(departmentName);
    }

    @Override
    public Long getDepartmentEmployeesNumber(final String departmentName) {
        return departmentEmployeeRepository.countByDepartmentName(departmentName);
    }

    private Employee buildEmployee(final EmployeeDTO employeeDTO, final Degree degree) {
        return new Employee(
                employeeDTO.getFirstName(),
                employeeDTO.getLastName(),
                degree,
                employeeDTO.getSalary(),
                new LinkedList<>());
    }

    private DepartmentEmployee buildDepartmentEmployee(final Department department,
                                                       final Employee employee, final Boolean isHead) {

        final DepartmentEmployee departmentEmployee = new DepartmentEmployee();
        departmentEmployee.setDepartment(department);
        departmentEmployee.setEmployee(employee);
        departmentEmployee.setIsHeadOfDepartment(isHead);

        employee.getDepartmentEmployees().add(departmentEmployee);
        return departmentEmployee;
    }
}
