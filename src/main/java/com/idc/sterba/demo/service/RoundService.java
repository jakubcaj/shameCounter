package com.idc.sterba.demo.service;

import com.idc.sterba.demo.dto.MatchDTO;

public interface RoundService {

    MatchDTO finishRound(Long matchId);

    MatchDTO updateRound(MatchDTO matchDTO);
}
