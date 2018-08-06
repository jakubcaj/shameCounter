package com.idc.sterba.demo.service.impl;

import com.idc.sterba.demo.dto.RegisterFormDTO;
import com.idc.sterba.demo.entity.Employee;
import com.idc.sterba.demo.entity.PlayerGroup;
import com.idc.sterba.demo.entity.secure.EmployeeMetadata;
import com.idc.sterba.demo.entity.secure.Role;
import com.idc.sterba.demo.exception.EmailAlreadyExistsException;
import com.idc.sterba.demo.exception.UsernameAlreadyExistsException;
import com.idc.sterba.demo.repository.EmployeeMetadataRepository;
import com.idc.sterba.demo.repository.EmployeeRepository;
import com.idc.sterba.demo.service.EmployeeService;
import com.idc.sterba.demo.service.PlayerGroupService;
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
    private final PlayerGroupService playerGroupService;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMetadataRepository employeeMetadataRepository,
                               RoleService roleService, SecurityService securityService, PlayerGroupService playerGroupService) {
        this.employeeRepository = employeeRepository;
        this.roleService = roleService;
        this.employeeMetadataRepository = employeeMetadataRepository;
        this.securityService = securityService;
        this.playerGroupService = playerGroupService;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll(new Sort(Sort.Direction.ASC, "lastName"));
    }

    @Override
    public List<Employee> getEmployeesByTerm(String term) {
        return employeeRepository.findAllBySearchTerm(term);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.getOne(id);
    }

    @Override
    public List<Employee> getEmployeesByGroup(Long groupId) {
        return employeeRepository.findAllByPlayerGroupId(groupId);
    }

    @Override
    public void registerEmployee(RegisterFormDTO registerFormDTO) {
        validateRegisterForm(registerFormDTO);
        registerFormDTO.setPassword(securityService.hashPassword(registerFormDTO.getPassword()));

        Role role = roleService.getRoleById(1L);
        Employee employee = new Employee(registerFormDTO, role);
        PlayerGroup playerGroup = playerGroupService.getPlayerGroupById(1L);
        employee.setPlayerGroup(List.of(playerGroup));

        employeeRepository.save(employee);

        EmployeeMetadata employeeMetadata = new EmployeeMetadata(registerFormDTO, employee);
        employeeMetadataRepository.save(employeeMetadata);
    }

    private void validateRegisterForm(RegisterFormDTO registerFormDTO) {
        checkIfUsernameIsUnique(registerFormDTO.getUsername());
        checkIfEmailIsUnique(registerFormDTO.getEmail());
    }

    private void checkIfUsernameIsUnique(String username) {
        if (employeeRepository.findByUsername(username) != null) {
            throw new UsernameAlreadyExistsException();
        }
    }

    private void checkIfEmailIsUnique(String email) {
        if (employeeRepository.findByEmail(email) != null) {
            throw new EmailAlreadyExistsException();
        }
    }
}
