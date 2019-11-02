package ru.lanit.emplyeebot.controllers;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lanit.emplyeebot.entities.Employee;
import ru.lanit.emplyeebot.services.EmployeeService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/employee")
public class EmployeeController {

    @NonNull
    private EmployeeService employeeService;

    @GetMapping
    public List<Employee> employees() {
        log.error("I am here!");
        return employeeService.employees();
    }

    @GetMapping("/id")
    public Employee getById(@PathVariable long id) {
        return employeeService.getById(id);
    }
}
