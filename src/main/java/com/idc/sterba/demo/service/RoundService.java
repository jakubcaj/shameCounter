package com.idc.sterba.demo.service;

import com.idc.sterba.demo.dto.MatchDTO;
import com.idc.sterba.demo.dto.ScoreDTO;
import com.idc.sterba.demo.entity.Round;

public interface RoundService {

    MatchDTO finishRound(Long matchId);

    void finishRound(Round round);

    MatchDTO updateRound(MatchDTO matchDTO);

    ScoreDTO getScoreOfRound(Long matchId);
}
