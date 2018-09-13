package com.idc.sterba.demo.entity.report;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.annotation.concurrent.Immutable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Immutable
@Table(name = "employee_matches_report", schema = "report")
public class EmployeeMatchesReport {

    @Id
    @Column(name = "row_number")
    private Long id;

    @Column(name = "employee_id")
    @JsonIgnore
    private Long employeeId;

    @JsonIgnore
    private String firstname;

    @JsonIgnore
    private String lastname;

    @Column(name = "team_mate")
    private String teamMate;

    private String opponents;

    private Boolean won;

    public Long getEmployeeId() {
        return employeeId;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getTeamMate() {
        return teamMate;
    }

    public String getOpponents() {
        return opponents;
    }

    public Boolean getWon() {
        return won;
    }

}
