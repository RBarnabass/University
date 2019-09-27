package com.junior.university.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.junior.university.model.Employee;
import com.junior.university.service.ConsoleService;
import com.junior.university.service.DepartmentService;
import com.junior.university.service.EmployeeService;
import com.junior.university.exceptions.DataNotFoundException;

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

        if (departmentStatistic.isEmpty()) {
            throw new DataNotFoundException("Department - {" + departmentName + "} has not any employee !");
        }

        final StringBuilder stringBuilder = new StringBuilder();

        departmentStatistic.keySet().forEach(key -> {
            stringBuilder.append(key).append(" - ").append(departmentStatistic.get(key)).append("\n");
        });

        return stringBuilder.toString();
    }

    @Override
    public String showAverageSalary(final String departmentName) {
        final Long averageSalaryByDepartmentName = departmentService.getAverageSalaryByDepartmentName(departmentName);

        if (averageSalaryByDepartmentName == null) {
            throw new DataNotFoundException("Department - {" + departmentName + "} has not any employee !");
        }

        return "The average salary of " + departmentName + " is - " + averageSalaryByDepartmentName + ".";
    }

    @Override
    public String showCountOfEmployee(final String departmentName) {
        final Long departmentEmployeesNumber = departmentService.getDepartmentEmployeesNumber(departmentName);

        if (departmentEmployeesNumber == null || departmentEmployeesNumber == 0) {
            throw new DataNotFoundException("Department - {" + departmentName + "} has not any employee !");
        }

        return "The number of employees in " + departmentName + " department is - " + departmentEmployeesNumber + ".";
    }

    @Override
    public String globalSearch(final String search) {
        final List<Employee> result = employeeService.search(search);

        final String found = result.stream()
                .map(emp -> emp.getFirstName() + " " + emp.getLastName())
                .collect(Collectors.joining(", "));

        if (StringUtils.isEmpty(found)) {
            throw new DataNotFoundException("Has not any result for - {" + search + "}");
        }

        return found;
    }
}
