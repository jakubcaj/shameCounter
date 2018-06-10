package com.idc.sterba.demo.dto;

public class ScoreDTO {
    private Long blueTeam;
    private Long redTeam;

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
}
