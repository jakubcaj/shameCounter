package com.idc.sterba.demo.dto;

import com.idc.sterba.demo.entity.Employee;
import com.idc.sterba.demo.entity.enums.GoalTypeEnum;

public class PlayerGoalDTO {
    private Long matchId;
    private Long roundId;
    private Employee employee;
    private GoalTypeEnum goalType;

    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
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

    public Long getRoundId() {
        return roundId;
    }

    public void setRoundId(Long roundId) {
        this.roundId = roundId;
    }
}
