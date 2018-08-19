package com.idc.sterba.demo.service.impl;

import com.idc.sterba.demo.dto.MatchDTO;
import com.idc.sterba.demo.dto.PlayerGoalDTO;
import com.idc.sterba.demo.dto.ScoreDTO;
import com.idc.sterba.demo.entity.Match;
import com.idc.sterba.demo.entity.Round;
import com.idc.sterba.demo.entity.Team;
import com.idc.sterba.demo.entity.TeamScore;
import com.idc.sterba.demo.repository.MatchRepository;
import com.idc.sterba.demo.repository.TeamRepository;
import com.idc.sterba.demo.service.MatchService;
import com.idc.sterba.demo.service.RoundService;
import com.idc.sterba.demo.service.SecurityService;
import com.idc.sterba.demo.util.ScoreUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchServiceImpl implements MatchService {

    private MatchRepository matchRepository;
    private TeamRepository teamRepository;
    private RoundService roundService;
    private SecurityService securityService;

    public MatchServiceImpl(MatchRepository matchRepository, TeamRepository teamRepository,
                            RoundService roundService, SecurityService securityService) {
        this.matchRepository = matchRepository;
        this.teamRepository = teamRepository;
        this.roundService = roundService;
        this.securityService = securityService;
    }

    @Override
    public Match getMatch(Long id) {
        return matchRepository.getMatchByFinishedAndId(false, id);
    }

    @Override
    public MatchDTO getMatchDto(Long id) {
        Match match = getMatch(id);

        if(match == null) {
            return null;
        }
       return new MatchDTO(match);

    }

    @Override
    public List<MatchDTO> getAllUnfinishedMatches() {
        return matchRepository.findAllUnfinishedMatchesByEmployee_id(this.securityService.getLoggedUser().getId())
                .stream().map(MatchDTO::new).collect(Collectors.toList());
    }

    @Override
    public ScoreDTO getScore(Long matchId) {
        Match match = getMatch(matchId);
        return ScoreUtil.getScoreForMatch(match);
    }

    @Override
    public void finishMatch(Long matchId) {
        Match match = getMatch(matchId);
        match.setFinished(true);
        this.matchRepository.save(match);
        this.roundService.finishRound(match.getRoundList().stream().filter(Round::isRunning).collect(Collectors.toList()).get(0));
    }

    @Override
    public Match saveMatch(MatchDTO matchDTO) {
        Match match = new Match(matchDTO);
        matchRepository.save(match);
        matchDTO.setMatchId(match.getId());
        return match;
    }

    @Override
    public TeamScore saveGoal(PlayerGoalDTO playerGoalDTO) {
        Team team = teamRepository.getTeamByTeamPlayerList_Employee_IdAndRound_Id(playerGoalDTO.getEmployee().getId(), playerGoalDTO.getRoundId());
        TeamScore teamScore = new TeamScore(team, playerGoalDTO.getEmployee(), playerGoalDTO.getGoalType());

        team.getTeamScoreList().add(teamScore);
        teamRepository.save(team);

        return team.getTeamScoreList().get(team.getTeamScoreList().size() -1);
    }


}
