package com.idc.sterba.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.idc.sterba.demo.entity.enums.GoalTypeEnum;
import org.eclipse.persistence.annotations.Cache;

import javax.persistence.*;

@Entity
@Cache(shared = false)
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

    @Column(name = "goal_type")
    @Enumerated(EnumType.STRING)
    private GoalTypeEnum goalType;

    @OneToOne
    @JsonIgnore
    private Employee loggedBy;

    public TeamScore() {
    }

    public TeamScore(Team team, Employee employee, GoalTypeEnum goalType, Employee loggedBy) {
        this.team = team;
        this.employee = employee;
        this.goalType = goalType;
        this.loggedBy = loggedBy;
    }

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

    public GoalTypeEnum getGoalType() {
        return goalType;
    }

    public void setGoalType(GoalTypeEnum goalType) {
        this.goalType = goalType;
    }

    public Employee getLoggedBy() {
        return loggedBy;
    }

    public void setLoggedBy(Employee loggedBy) {
        this.loggedBy = loggedBy;
    }
}
