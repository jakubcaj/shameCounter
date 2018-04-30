package com.idc.sterba.demo.service.impl;

import com.idc.sterba.demo.dto.RegisterFormDTO;
import com.idc.sterba.demo.entity.Employee;
import com.idc.sterba.demo.entity.PlayerGroup;
import com.idc.sterba.demo.entity.secure.EmployeeMetadata;
import com.idc.sterba.demo.entity.secure.Role;
import com.idc.sterba.demo.repository.EmployeeMetadataRepository;
import com.idc.sterba.demo.repository.EmployeeRepository;
import com.idc.sterba.demo.repository.PlayerGroupRepository;
import com.idc.sterba.demo.repository.RoleRepository;
import com.idc.sterba.demo.service.EmployeeService;
import com.idc.sterba.demo.service.RoleService;
import com.idc.sterba.demo.service.SecurityService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMetadataRepository employeeMetadataRepository;
    private final RoleService roleService;
    private final SecurityService securityService;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMetadataRepository employeeMetadataRepository, RoleService roleService, SecurityService securityService) {
        this.employeeRepository = employeeRepository;
        this.roleService = roleService;
        this.employeeMetadataRepository = employeeMetadataRepository;
        this.securityService = securityService;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll(new Sort(Sort.Direction.ASC, "lastName"));
    }

    public List<Employee> getEmployeesByTerm(String term) {
        return employeeRepository.findAllBySearchTerm(term);
    }

    public void registerEmployee(RegisterFormDTO registerFormDTO) {
        registerFormDTO.setPassword(securityService.hashPassword(registerFormDTO.getPassword()));

        Role role = roleService.getRoleById(1L);
        Employee employee = new Employee(registerFormDTO, role);
        employeeRepository.save(employee);

        EmployeeMetadata employeeMetadata = new EmployeeMetadata(registerFormDTO, employee);
        employeeMetadataRepository.save(employeeMetadata);
    }
}
