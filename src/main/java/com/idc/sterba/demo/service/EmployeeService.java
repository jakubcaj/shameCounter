package com.idc.sterba.demo.service;

import com.idc.sterba.demo.dto.RegisterFormDTO;
import com.idc.sterba.demo.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();

    List<Employee> getEmployeesByTerm(String term);

    Employee getEmployeeById(Long id);

    List<Employee> getEmployeesByGroup(Long groupId);

    void registerEmployee(RegisterFormDTO registerFormDTO);

    void changePassword(String password);
}
