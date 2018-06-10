package com.idc.sterba.demo.dto;

import com.idc.sterba.demo.entity.ColorEnum;
import com.idc.sterba.demo.entity.Employee;
import com.idc.sterba.demo.entity.Match;
import com.idc.sterba.demo.entity.PlayerRoleEnum;

public class MatchDTO {

    private Long matchId;
    private Employee blueGoalman;
    private Employee blueAttacker;
    private Employee redGoalman;
    private Employee redAttacker;

    public MatchDTO() {

    }

    public MatchDTO(Match match) {
        this.matchId = match.getId();
        this.blueGoalman = match.getPlayer(PlayerRoleEnum.GOALMAN, ColorEnum.BLUE);
        this.blueAttacker = match.getPlayer(PlayerRoleEnum.ATTACKER, ColorEnum.BLUE);
        this.redGoalman = match.getPlayer(PlayerRoleEnum.GOALMAN, ColorEnum.RED);
        this.redAttacker = match.getPlayer(PlayerRoleEnum.ATTACKER, ColorEnum.RED);
    }

    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }

    public Employee getBlueGoalman() {
        return blueGoalman;
    }

    public void setBlueGoalman(Employee blueGoalman) {
        this.blueGoalman = blueGoalman;
    }

    public Employee getBlueAttacker() {
        return blueAttacker;
    }

    public void setBlueAttacker(Employee blueAttacker) {
        this.blueAttacker = blueAttacker;
    }

    public Employee getRedGoalman() {
        return redGoalman;
    }

    public void setRedGoalman(Employee redGoalman) {
        this.redGoalman = redGoalman;
    }

    public Employee getRedAttacker() {
        return redAttacker;
    }

    public void setRedAttacker(Employee redAttacker) {
        this.redAttacker = redAttacker;
    }
}
