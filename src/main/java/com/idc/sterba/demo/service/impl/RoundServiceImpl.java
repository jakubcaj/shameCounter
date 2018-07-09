package com.idc.sterba.demo.service.impl;

import com.idc.sterba.demo.dto.MatchDTO;
import com.idc.sterba.demo.entity.Round;
import com.idc.sterba.demo.repository.RoundRepository;
import com.idc.sterba.demo.service.RoundService;
import com.idc.sterba.demo.util.PlayerUtil;
import org.springframework.stereotype.Service;

@Service
public class RoundServiceImpl implements RoundService {

    private RoundRepository roundRepository;

    public RoundServiceImpl(RoundRepository roundRepository) {
        this.roundRepository = roundRepository;
    }

    @Override
    public MatchDTO finishRound(Long matchId) {
        Round round = roundRepository.getRoundByMatch_IdAndRunning(matchId, true);
        round.setRunning(false);

        Round newRound = new Round(round);
        roundRepository.save(newRound);
        return PlayerUtil.createMatchDTOFromMatch(newRound.getMatch());
    }

    @Override
    public MatchDTO updateRound(MatchDTO matchDTO) {
        Round round = roundRepository.getRoundByMatch_IdAndRunning(matchDTO.getMatchId(), true);
        round.updateEmployeesPosition(matchDTO);
        roundRepository.save(round);

        return PlayerUtil.createMatchDTOFromMatch(round.getMatch());
    }
}
