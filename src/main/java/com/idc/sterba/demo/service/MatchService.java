package com.idc.sterba.demo.service;

import com.idc.sterba.demo.dto.MatchDTO;
import com.idc.sterba.demo.dto.PlayerGoalDTO;
import com.idc.sterba.demo.dto.ScoreDTO;
import com.idc.sterba.demo.entity.Match;
import com.idc.sterba.demo.entity.TeamScore;

import java.util.List;

public interface MatchService {
    Match getMatch(Long id);

    Match saveMatch(MatchDTO matchDTO);

    TeamScore saveGoal(PlayerGoalDTO playerGoalDTO);

    MatchDTO getMatchDto(Long id);

    List<MatchDTO> getAllUnfinishedMatches();

    ScoreDTO getScore(Long matchId);

    void finishMatch(Long matchId);
}
