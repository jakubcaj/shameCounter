package com.idc.sterba.demo.service.impl;

import com.idc.sterba.demo.dto.MatchDTO;
import com.idc.sterba.demo.dto.ScoreDTO;
import com.idc.sterba.demo.entity.Match;
import com.idc.sterba.demo.entity.Round;
import com.idc.sterba.demo.repository.RoundRepository;
import com.idc.sterba.demo.service.MatchService;
import com.idc.sterba.demo.service.RoundService;
import com.idc.sterba.demo.service.TeamService;
import com.idc.sterba.demo.util.ScoreUtil;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoundServiceImpl implements RoundService {

    private RoundRepository roundRepository;
    private MatchService matchService;
    private TeamService teamService;

    public RoundServiceImpl(RoundRepository roundRepository,@Lazy MatchService matchService, TeamService teamService) {
        this.roundRepository = roundRepository;
        this.matchService = matchService;
        this.teamService = teamService;
    }

    @Override
    public MatchDTO finishRound(Long matchId) {
        Round round = getActiveRound(matchId);
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
        Round round = getActiveRound(matchDTO.getMatchId());
        round.updateEmployeesPosition(matchDTO);
        roundRepository.save(round);

        return new MatchDTO(round.getMatch());
    }

    @Override
    public ScoreDTO getScoreOfRound(Long matchId) {
        Match match = matchService.getMatch(matchId);

        return ScoreUtil.getScoreForRunningRound(match);
    }

    @Override
    public void deleteRunningRound(Long matchId) {
        Round runningRound = getActiveRound(matchId);
        teamService.deleteTeamsInRound(runningRound.getId());
        roundRepository.delete(runningRound);
    }

    @Override
    public void setLastRoundAsRunning(Long matchId) {
        Round round = roundRepository.findFirstByMatch_IdOrderByIdDesc(matchId);
        round.setRunning(true);
        roundRepository.save(round);
    }

    @Override
    public Round getActiveRound(Long matchId) {
        return roundRepository.findByMatch_IdAndRunning(matchId, true);
    }

}
