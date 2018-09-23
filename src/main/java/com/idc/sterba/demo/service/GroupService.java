package com.idc.sterba.demo.service;

import com.idc.sterba.demo.dto.EmployeeWithCoeficientDTO;

import java.util.List;

public interface GroupService {

    void updateCoeficientForEmployees(List<EmployeeWithCoeficientDTO> employeeWithCoeficientDTOList, Long groupId);
}
