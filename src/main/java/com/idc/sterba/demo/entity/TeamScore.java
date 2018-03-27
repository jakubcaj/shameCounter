package com.idc.sterba.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class TeamScore {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teamScoreIdSeq")
    @SequenceGenerator(name = "teamScoreIdSeq", sequenceName = "team_score_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JsonBackReference
    private Team team;

    @OneToOne
    private Employee employee;

    @OneToOne
    private GoalType goalType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public GoalType getGoalType() {
        return goalType;
    }

    public void setGoalType(GoalType goalType) {
        this.goalType = goalType;
    }
}
