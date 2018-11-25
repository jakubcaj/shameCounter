package com.idc.sterba.demo.service.impl;

import com.idc.sterba.demo.dto.RevertedScoreDTO;
import com.idc.sterba.demo.entity.Round;
import com.idc.sterba.demo.entity.Team;
import com.idc.sterba.demo.entity.TeamScore;
import com.idc.sterba.demo.exception.runtime.DefaultShameException;
import com.idc.sterba.demo.repository.TeamRepository;
import com.idc.sterba.demo.repository.TeamScoreRepository;
import com.idc.sterba.demo.service.MatchService;
import com.idc.sterba.demo.service.RoundService;
import com.idc.sterba.demo.service.TeamScoreService;
import com.idc.sterba.demo.util.ScoreUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamScoreServiceImpl implements TeamScoreService {

    private final TeamScoreRepository teamScoreRepository;
    private final TeamRepository teamRepository;
    private final RoundService roundService;
    private final MatchService matchService;

    public TeamScoreServiceImpl(TeamScoreRepository teamScoreRepository, TeamRepository teamRepository,
                                RoundService roundService, MatchService matchService) {
        this.teamScoreRepository = teamScoreRepository;
        this.teamRepository = teamRepository;
        this.roundService = roundService;
        this.matchService = matchService;
    }

    @Override
    public RevertedScoreDTO removeTeamScore(Long teamScoreId) {
        TeamScore realTeamScore = teamScoreRepository.findById(teamScoreId).orElseThrow(DefaultShameException::new);
        Long matchId = realTeamScore.getTeam().getRound().getMatch().getId();
        RevertedScoreDTO revertedScoreDTO = new RevertedScoreDTO();

        if (!realTeamScore.getTeam().getRound().isRunning() && realTeamScore.getTeam().getRound().getMatch().getRoundList().size() > 1) {
            deleteCurrentRound(realTeamScore, revertedScoreDTO, matchId);
        }
        teamScoreRepository.delete(realTeamScore);
        Team team = realTeamScore.getTeam();
        team.setTeamScoreList(team.getTeamScoreList().stream().filter(x -> !x.getId().equals(realTeamScore.getId())).collect(Collectors.toList()));
        teamRepository.save(team);

        revertedScoreDTO.setScore(ScoreUtil.getScoreForRunningRound(team.getRound().getMatch()));
        revertedScoreDTO.setRoundScore(matchService.getScore(matchId));
        return revertedScoreDTO;
    }

    @Override
    public List<TeamScore> getAllTeamScore(Long matchId) {
        return teamScoreRepository.findAllByTeam_Round_Match_Id(matchId);
    }

    private void deleteCurrentRound(TeamScore realTeamScore, RevertedScoreDTO revertedScoreDTO, Long matchId) {
        roundService.deleteRunningRound(matchId);
        roundService.setLastRoundAsRunning(matchId);
        Round activeRound = roundService.getActiveRound(matchId);
        revertedScoreDTO.setRoundId(activeRound.getId());
    }


}
