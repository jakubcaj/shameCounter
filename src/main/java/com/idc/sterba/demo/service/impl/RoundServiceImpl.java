package com.idc.sterba.demo.service.impl;

import com.idc.sterba.demo.dto.MatchDTO;
import com.idc.sterba.demo.entity.Round;
import com.idc.sterba.demo.repository.MatchRepository;
import com.idc.sterba.demo.repository.RoundRepository;
import com.idc.sterba.demo.service.RoundService;
import com.idc.sterba.demo.util.PlayerUtil;
import org.springframework.stereotype.Service;

@Service
public class RoundServiceImpl implements RoundService {

    private RoundRepository roundRepository;
    private MatchRepository matchRepository;

    public RoundServiceImpl(RoundRepository roundRepository, MatchRepository matchRepository) {
        this.roundRepository = roundRepository;
        this.matchRepository = matchRepository;
    }

    @Override
    public MatchDTO finishRound(Long matchId) {
        Round round = roundRepository.getRoundByMatch_IdAndRunning(matchId, true);
        round.setRunning(false);

        Round newRound = new Round(round);
        roundRepository.save(newRound);
        return PlayerUtil.createMatchDTOFromMatch(newRound.getMatch());
    }
}
