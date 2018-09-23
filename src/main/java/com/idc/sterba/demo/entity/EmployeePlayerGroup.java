package com.idc.sterba.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;


@Entity(name = "employee_player_group")
public class EmployeePlayerGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "empGroupIdSeq")
    @SequenceGenerator(name = "empGroupIdSeq", sequenceName = "employee_player_group_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JsonIgnore
    private Employee employee;

    @OneToOne
    private PlayerGroup playerGroup;

    @Column(name = "chance_coeficient")
    private Double chanceCoeficient = 1D;

    public EmployeePlayerGroup() {

    }

    public EmployeePlayerGroup(Employee employee, PlayerGroup playerGroup) {
        this.employee = employee;
        this.playerGroup = playerGroup;
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

    public PlayerGroup getPlayerGroup() {
        return playerGroup;
    }

    public void setPlayerGroup(PlayerGroup playerGroup) {
        this.playerGroup = playerGroup;
    }

    public Double getChanceCoeficient() {
        return chanceCoeficient;
    }

    public void setChanceCoeficient(Double chanceCoeficient) {
        this.chanceCoeficient = chanceCoeficient;
    }
}

