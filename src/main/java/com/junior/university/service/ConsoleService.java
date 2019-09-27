package com.junior.university.service;

public interface ConsoleService {

    String whoIsHeadOfDepartment(final String departmentName);
    String showDepartmentStatistic(final String departmentName);
    String showAverageSalary(final String departmentName);
    String showCountOfEmployee(final String departmentName);
    String globalSearch(final String search);
}
