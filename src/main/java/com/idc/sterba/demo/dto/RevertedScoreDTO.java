package com.idc.sterba.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RevertedScoreDTO extends ScoreDTO {
    private Long roundId;
    private ScoreDTO roundScore;

    public RevertedScoreDTO() {
    }

    public Long getRoundId() {
        return roundId;
    }

    public void setRoundId(Long roundId) {
        this.roundId = roundId;
    }

    public ScoreDTO getRoundScore() {
        return roundScore;
    }

    public void setRoundScore(ScoreDTO roundScore) {
        this.roundScore = roundScore;
    }

    public void setScore(ScoreDTO scoreDTO) {
        this.setBlueTeam(scoreDTO.getBlueTeam());
        this.setRedTeam(scoreDTO.getRedTeam());
        this.setTeamScore(scoreDTO.getTeamScore());
    }

    @JsonProperty(value = "wasRoundReverted")
    public boolean wasRoundReverted() {
        return roundId != null;
    }

}
