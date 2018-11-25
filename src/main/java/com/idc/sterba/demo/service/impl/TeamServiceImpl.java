package com.idc.sterba.demo.service.impl;

import com.idc.sterba.demo.entity.Team;
import com.idc.sterba.demo.repository.TeamRepository;
import com.idc.sterba.demo.service.TeamPlayerService;
import com.idc.sterba.demo.service.TeamService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TeamServiceImpl implements TeamService {

    private TeamRepository teamRepository;
    private TeamPlayerService teamPlayerService;

    public TeamServiceImpl(TeamRepository teamRepository, TeamPlayerService teamPlayerService) {
        this.teamRepository = teamRepository;
        this.teamPlayerService = teamPlayerService;
    }

    @Override
    public void deleteTeamsInRound(Long roundId) {
        List<Team> teams = teamRepository.findAllByRound_Id(roundId);
        for (Team team : teams) {
            teamPlayerService.deleteTeamPlayersInTeam(team.getId());
            teamRepository.delete(team);
        }
//        teamRepository.deleteAll(teams);
    }
}
