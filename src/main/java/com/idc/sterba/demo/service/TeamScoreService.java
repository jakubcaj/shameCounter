package com.idc.sterba.demo.service;

import com.idc.sterba.demo.dto.RevertedScoreDTO;
import com.idc.sterba.demo.entity.TeamScore;

import java.util.List;

public interface TeamScoreService {
    RevertedScoreDTO removeTeamScore(Long teamScoreId);

    List<TeamScore> getAllTeamScore(Long matchId);
}
