package com.idc.sterba.demo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.idc.sterba.demo.dto.RegisterFormDTO;
import com.idc.sterba.demo.entity.secure.EmployeeRole;
import com.idc.sterba.demo.entity.secure.Role;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "empIdSeq")
    @SequenceGenerator(name = "empIdSeq", sequenceName = "employee_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private List<EmployeePlayerGroup> employeePlayerGroupList;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private List<EmployeeRole> roles;

    public Employee() {
    }

    public Employee(RegisterFormDTO registerFormDTO, Role role) {
        this.firstName = registerFormDTO.getFirstName();
        this.lastName = registerFormDTO.getLastName();

        roles = List.of(new EmployeeRole(role, this));
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

    public List<EmployeePlayerGroup> getPlayerGroup() {
        return this.employeePlayerGroupList;
    }

    public void setPlayerGroup(List<EmployeePlayerGroup> employeePlayerGroupList) {
        this.employeePlayerGroupList = employeePlayerGroupList;
    }

    public List<EmployeeRole> getRoles() {
        return roles;
    }

    public void setRoles(List<EmployeeRole> roles) {
        this.roles = roles;
    }

    @JsonProperty(value = "playerGroup")
    public List<PlayerGroup> getPlayerGroupList() {
        if (this.employeePlayerGroupList != null) {
            return this.employeePlayerGroupList.stream().map(EmployeePlayerGroup::getPlayerGroup).collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }
}
