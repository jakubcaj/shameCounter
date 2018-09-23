package com.idc.sterba.demo.dto;

import com.idc.sterba.demo.entity.Employee;
import com.idc.sterba.demo.entity.EmployeePlayerGroup;

import java.util.stream.Collectors;

public class EmployeeWithCoeficientDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private Double chanceCoeficient;
    private Double newChanceCoeficient;

    public EmployeeWithCoeficientDTO() {

    }

    public EmployeeWithCoeficientDTO(Employee employee, Long groupId) {
        this.id = employee.getId();
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.chanceCoeficient = employee.getPlayerGroup().stream()
                .filter(group -> group.getPlayerGroup().getId().equals(groupId))
                .map(EmployeePlayerGroup::getChanceCoeficient).collect(Collectors.toList()).get(0);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Double getChanceCoeficient() {
        return chanceCoeficient;
    }

    public void setChanceCoeficient(Double chanceCoeficient) {
        this.chanceCoeficient = chanceCoeficient;
    }

    public Double getNewChanceCoeficient() {
        return newChanceCoeficient;
    }

    public void setNewChanceCoeficient(Double newChanceCoeficient) {
        this.newChanceCoeficient = newChanceCoeficient;
    }
}
