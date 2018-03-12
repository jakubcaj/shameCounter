package com.idc.sterba.demo.service;

import com.idc.sterba.demo.entity.Employee;
import com.idc.sterba.demo.repository.EmployeeRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll(new Sort(Sort.Direction.ASC, "lastName"));
    }

}
