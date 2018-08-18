package com.idc.sterba.demo.dto;

import com.idc.sterba.demo.entity.Employee;
import com.idc.sterba.demo.entity.Match;
import com.idc.sterba.demo.entity.Round;
import com.idc.sterba.demo.entity.enums.ColorEnum;
import com.idc.sterba.demo.entity.enums.PlayerRoleEnum;

import java.util.stream.Collectors;

public class MatchDTO {

    private Long matchId;
    private Long roundId;
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
        this.roundId = match.getRoundList().stream().filter(Round::isRunning).collect(Collectors.toList()).get(0).getId();
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

    public Long getRoundId() {
        return roundId;
    }

    public void setRoundId(Long roundId) {
        this.roundId = roundId;
    }
}
