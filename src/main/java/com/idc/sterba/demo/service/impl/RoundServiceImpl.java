package com.idc.sterba.demo.service.impl;

import com.idc.sterba.demo.dto.MatchDTO;
import com.idc.sterba.demo.dto.ScoreDTO;
import com.idc.sterba.demo.entity.Match;
import com.idc.sterba.demo.entity.Round;
import com.idc.sterba.demo.repository.RoundRepository;
import com.idc.sterba.demo.service.MatchService;
import com.idc.sterba.demo.service.RoundService;
import com.idc.sterba.demo.util.ScoreUtil;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class RoundServiceImpl implements RoundService {

    private RoundRepository roundRepository;
    private MatchService matchService;

    public RoundServiceImpl(RoundRepository roundRepository,@Lazy MatchService matchService) {
        this.roundRepository = roundRepository;
        this.matchService = matchService;
    }

    @Override
    public MatchDTO finishRound(Long matchId) {
        Round round = roundRepository.getRoundByMatch_IdAndRunning(matchId, true);
        round.setRunning(false);

        Round newRound = new Round(round);
        roundRepository.save(newRound);
        return new MatchDTO(newRound.getMatch());
    }

    @Override
    public void finishRound(Round round) {
        round.setRunning(false);
        roundRepository.save(round);
    }

    @Override
    public MatchDTO updateRound(MatchDTO matchDTO) {
        Round round = roundRepository.getRoundByMatch_IdAndRunning(matchDTO.getMatchId(), true);
        round.updateEmployeesPosition(matchDTO);
        roundRepository.save(round);

        return new MatchDTO(round.getMatch());
    }

    @Override
    public ScoreDTO getScoreOfRound(Long matchId) {
        Match match = matchService.getMatch(matchId);

        return ScoreUtil.getScoreForRunningRound(match);
    }
}
