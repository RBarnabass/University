package com.junior.university.service.impl;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.junior.university.model.Employee;
import com.junior.university.service.ConsoleService;
import com.junior.university.service.DepartmentService;
import com.junior.university.service.EmployeeService;

@Service
public class ConsoleServiceImpl implements ConsoleService {

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    @Autowired
    public ConsoleServiceImpl(final EmployeeService employeeService, final DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }


    @Override
    public String whoIsHeadOfDepartment(final String departmentName) {
        final Employee headOfDepartment = departmentService.getHeadOfDepartment(departmentName);

        return "Head of " + departmentName + " department is "
                + headOfDepartment.getFirstName() + " " + headOfDepartment.getLastName() + ".";
    }

    @Override
    public String showDepartmentStatistic(final String departmentName) {
        final Map<String, Integer> departmentStatistic = departmentService.getDepartmentStatistic(departmentName);
        final StringBuilder stringBuilder = new StringBuilder();

        departmentStatistic.keySet().forEach(key -> {
            stringBuilder.append(key).append(" - ").append(departmentStatistic.get(key)).append("\n");
        });

        return stringBuilder.toString() + ".";
    }

    @Override
    public String showAverageSalary(final String departmentName) {
        final Long averageSalaryByDepartmentName = departmentService.getAverageSalaryByDepartmentName(departmentName);
        return "The average salary of " + departmentName + " is - " + averageSalaryByDepartmentName + ".";
    }

    @Override
    public String showCountOfEmployee(final String departmentName) {
        final Long departmentEmployeesNumber = departmentService.getDepartmentEmployeesNumber(departmentName);
        return "The number of employees in " + departmentName + " department is - " + departmentEmployeesNumber + ".";
    }

    @Override
    public String globalSearch(final String search) {
        final List<Employee> result = employeeService.search(search);
        return result.stream()
                .map(emp -> emp.getFirstName() + " " + emp.getLastName())
                .collect(Collectors.joining(", "));
    }
}
