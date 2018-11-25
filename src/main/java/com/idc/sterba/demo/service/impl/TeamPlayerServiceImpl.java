package com.idc.sterba.demo.service.impl;

import com.idc.sterba.demo.entity.TeamPlayer;
import com.idc.sterba.demo.repository.TeamPlayerRepository;
import com.idc.sterba.demo.service.TeamPlayerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TeamPlayerServiceImpl implements TeamPlayerService {

    private TeamPlayerRepository teamPlayerRepository;

    public TeamPlayerServiceImpl(TeamPlayerRepository teamPlayerRepository) {
        this.teamPlayerRepository = teamPlayerRepository;
    }


    @Override
    public void deleteTeamPlayersInTeam(Long teamId) {
        List<TeamPlayer> teamPlayers = teamPlayerRepository.findAllByTeam_Id(teamId);

        teamPlayerRepository.deleteAll(teamPlayers);
    }
}
