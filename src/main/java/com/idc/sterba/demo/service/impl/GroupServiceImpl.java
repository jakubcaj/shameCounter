package com.idc.sterba.demo.service.impl;

import com.idc.sterba.demo.dto.EmployeeWithCoeficientDTO;
import com.idc.sterba.demo.repository.EmployeePlayerGroupRepository;
import com.idc.sterba.demo.service.GroupService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    private EmployeePlayerGroupRepository employeePlayerGroupRepository;

    public GroupServiceImpl(EmployeePlayerGroupRepository employeePlayerGroupRepository) {
        this.employeePlayerGroupRepository = employeePlayerGroupRepository;
    }

    @Override
    public void updateCoeficientForEmployees(List<EmployeeWithCoeficientDTO> employeeWithCoeficientDTOList, Long groupId) {
        employeeWithCoeficientDTOList.stream().filter(e -> e.getNewChanceCoeficient() != null && !e.getChanceCoeficient().equals(e.getNewChanceCoeficient()))
                .forEach(e -> updateCoeficientForEmployee(e.getNewChanceCoeficient(), e.getId(), groupId));
    }

    private void updateCoeficientForEmployee(Double coeficient, Long employeeId, Long groupId) {
        employeePlayerGroupRepository.updateCoeficient(coeficient, employeeId, groupId);
    }
}
