package com.junior.university.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.junior.university.dto.DepartmentDTO;
import com.junior.university.dto.EmployeeDTO;
import com.junior.university.model.Department;
import com.junior.university.model.Employee;
import com.junior.university.service.DepartmentService;
import com.junior.university.service.EmployeeService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Validated
@RestController
@RequestMapping("/api/university")
public class DepartmentController {

    private final ModelMapper modelMapper;
    private final DepartmentService departmentService;
    private final ConsoleController consoleController;
    private final EmployeeService employeeService;

    @Autowired
    public DepartmentController(final ModelMapper modelMapper, final DepartmentService departmentService,
                                final ConsoleController consoleController, final EmployeeService employeeService) {

        this.modelMapper = modelMapper;
        this.departmentService = departmentService;
        this.consoleController = consoleController;
        this.employeeService = employeeService;
    }


    @GetMapping("/start")
    @ResponseStatus(HttpStatus.OK)
    public void start() {
        consoleController.start();
    }

    @PostMapping("/departments")
    @ResponseStatus(HttpStatus.CREATED)
    public DepartmentDTO add(@RequestBody @Valid final DepartmentDTO departmentDTO) {

        log.info("Income Department dto - {}", departmentDTO);
        Department department = modelMapper.map(departmentDTO, Department.class);
        Department add = departmentService.add(department);
        DepartmentDTO departmentDTO1 = modelMapper.map(add, DepartmentDTO.class);
        log.info("Outcome Department - {}", departmentDTO1);

        return departmentDTO1;
    }

    @PostMapping("/employees")
    @ResponseStatus(HttpStatus.CREATED)
    public DepartmentDTO addEmployee(@RequestBody @Valid final EmployeeDTO employeeDTO) {

        log.info("Income employee - {}", employeeDTO);

        Department department = departmentService.addEmployee(employeeDTO);
        DepartmentDTO map1 = modelMapper.map(department, DepartmentDTO.class);

        log.info("Outcome department - {}", map1);

        return map1;
    }

    @GetMapping("/departments/head")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeDTO getHead(@RequestParam final String departmentName) {

        final Employee headOfDepartment = departmentService.getHeadOfDepartment(departmentName);
        return modelMapper.map(headOfDepartment, EmployeeDTO.class);
    }

    @GetMapping("/departments/statistic")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Integer> getStatistic(@RequestParam final String departmentName) {

        return departmentService.getDepartmentStatistic(departmentName);
    }

    @GetMapping("/departments/salary")
    @ResponseStatus(HttpStatus.OK)
    public Long getAverageSalary(@RequestParam final String departmentName) {

        return departmentService.getAverageSalaryByDepartmentName(departmentName);
    }

    @GetMapping("/departments/number")
    @ResponseStatus(HttpStatus.OK)
    public Long getEmployeesNumber(@RequestParam final String departmentName) {

        return departmentService.getDepartmentEmployeesNumber(departmentName);
    }

    @GetMapping("/departments/search")
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeDTO> search(@RequestParam final String search) {

        List<Employee> search1 = employeeService.search(search);
        return search1.stream().map(emp -> modelMapper.map(emp, EmployeeDTO.class)).collect(Collectors.toList());
    }
}
