package com.idc.sterba.demo.entity.secure;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.idc.sterba.demo.entity.Employee;

import javax.persistence.*;

@Entity
@Table(name = "employee_role", schema = "secure")
public class EmployeeRole {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employeeRoleIdSeq")
    @SequenceGenerator(name = "employeeRoleIdSeq", sequenceName = "employee_role_id_seq", allocationSize = 1, schema = "secure")
    private Long id;

    @ManyToOne
    @JsonBackReference
    private Employee employee;

    @OneToOne
    private Role role;

    public EmployeeRole() {

    }

    public EmployeeRole(Role role, Employee employee) {
        this.role = role;
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
