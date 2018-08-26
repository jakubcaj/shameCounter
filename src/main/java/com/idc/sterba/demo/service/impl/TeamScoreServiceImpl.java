package com.idc.sterba.demo.service.impl;

import com.idc.sterba.demo.dto.ScoreDTO;
import com.idc.sterba.demo.entity.Team;
import com.idc.sterba.demo.entity.TeamScore;
import com.idc.sterba.demo.repository.TeamRepository;
import com.idc.sterba.demo.repository.TeamScoreRepository;
import com.idc.sterba.demo.service.TeamScoreService;
import com.idc.sterba.demo.util.ScoreUtil;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class TeamScoreServiceImpl implements TeamScoreService {

    private final TeamScoreRepository teamScoreRepository;
    private final TeamRepository teamRepository;

    public TeamScoreServiceImpl(TeamScoreRepository teamScoreRepository, TeamRepository teamRepository) {
        this.teamScoreRepository = teamScoreRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public ScoreDTO removeTeamScore(TeamScore teamScore, Long matchId) {
        TeamScore realTeamScore = teamScoreRepository.getOne(teamScore.getId());
        teamScoreRepository.delete(realTeamScore);
        Team team = realTeamScore.getTeam();
        team.setTeamScoreList(team.getTeamScoreList().stream().filter(x -> !x.getId().equals(realTeamScore.getId())).collect(Collectors.toList()));
        teamRepository.save(team);

        return ScoreUtil.getScoreForRunningRound(team.getRound().getMatch());
    }
}
