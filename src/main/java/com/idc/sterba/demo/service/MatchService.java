package com.idc.sterba.demo.service;

import com.idc.sterba.demo.dto.MatchDTO;
import com.idc.sterba.demo.dto.PlayerGoalDTO;
import com.idc.sterba.demo.dto.ScoreDTO;
import com.idc.sterba.demo.entity.Match;

public interface MatchService {
    Match getMatch(Long id);

    Match saveMatch(MatchDTO matchDTO);

    void saveGoal(PlayerGoalDTO playerGoalDTO);

    ScoreDTO getScoreOfRound(Long matchId);
}
