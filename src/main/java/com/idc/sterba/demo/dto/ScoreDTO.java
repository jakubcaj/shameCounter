package com.idc.sterba.demo.dto;

import com.idc.sterba.demo.entity.TeamScore;

public class ScoreDTO {
    private Long blueTeam;
    private Long redTeam;
    private TeamScore teamScore;

    public ScoreDTO() {
        this.blueTeam = 0L;
        this.redTeam = 0L;
    }

    public ScoreDTO(Long blueTeam, Long redTeam) {
        this.blueTeam = blueTeam;
        this.redTeam = redTeam;
    }

    public Long getBlueTeam() {
        return blueTeam;
    }

    public void setBlueTeam(Long blueTeam) {
        this.blueTeam = blueTeam;
    }

    public Long getRedTeam() {
        return redTeam;
    }

    public void setRedTeam(Long redTeam) {
        this.redTeam = redTeam;
    }

    public void addBlueTeam() {
        this.blueTeam++;
    }

    public void addRedTEam() {
        this.redTeam++;
    }

    public TeamScore getTeamScore() {
        return teamScore;
    }

    public void setTeamScore(TeamScore teamScore) {
        this.teamScore = teamScore;
    }
}
