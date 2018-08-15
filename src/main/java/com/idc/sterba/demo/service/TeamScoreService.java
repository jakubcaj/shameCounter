package com.idc.sterba.demo.service;

import com.idc.sterba.demo.dto.ScoreDTO;
import com.idc.sterba.demo.entity.TeamScore;

public interface TeamScoreService {
    ScoreDTO removeTeamScore(TeamScore teamScore, Long matchId);
}
