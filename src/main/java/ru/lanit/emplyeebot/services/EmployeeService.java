package ru.lanit.emplyeebot.services;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lanit.emplyeebot.entities.Employee;
import ru.lanit.emplyeebot.repos.EmployeeRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    @NonNull
    private EmployeeRepository employeeRepository;

    public List<Employee> employees() {
        return employeeRepository.findAll();
    }

    public Employee getById(long id) {
        return employeeRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
