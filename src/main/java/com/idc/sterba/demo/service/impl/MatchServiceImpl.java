package com.idc.sterba.demo.service.impl;

import com.idc.sterba.demo.dto.MatchDTO;
import com.idc.sterba.demo.dto.PlayerGoalDTO;
import com.idc.sterba.demo.dto.ScoreDTO;
import com.idc.sterba.demo.entity.*;
import com.idc.sterba.demo.repository.MatchRepository;
import com.idc.sterba.demo.repository.TeamRepository;
import com.idc.sterba.demo.repository.TeamScoreRepository;
import com.idc.sterba.demo.service.MatchService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchServiceImpl implements MatchService {

    private MatchRepository matchRepository;
    private TeamRepository teamRepository;
    private TeamScoreRepository teamScoreRepository;

    public MatchServiceImpl(MatchRepository matchRepository, TeamRepository teamRepository,
                            TeamScoreRepository teamScoreRepository) {
        this.matchRepository = matchRepository;
        this.teamRepository = teamRepository;
        this.teamScoreRepository = teamScoreRepository;
    }

    @Override
    public Match getMatch(Long id) {
        return matchRepository.getOne(id);
    }

    @Override
    public MatchDTO getMatchDto(Long id) {
       return new MatchDTO(matchRepository.getOne(id));

    }

    @Override
    public Match saveMatch(MatchDTO matchDTO) {
        Match match = new Match(matchDTO);
        matchRepository.save(match);
        matchDTO.setMatchId(match.getId());
        return match;
    }

    @Override
    public void saveGoal(PlayerGoalDTO playerGoalDTO) {
        Team team = teamRepository.getTeamByMatchId(playerGoalDTO.getMatchId(), playerGoalDTO.getEmployee().getId());
        TeamScore teamScore = new TeamScore(team, playerGoalDTO.getEmployee(), new GoalType(playerGoalDTO.getGoalType()));

        team.getTeamScoreList().add(teamScore);
        teamRepository.save(team);
    }

    @Override
    public ScoreDTO getScoreOfRound(Long matchId) {
        Match match = matchRepository.getOne(matchId);

        List<TeamScore> blueTeam = getTeamScoreFromMatch(match, ColorEnum.BLUE);
        List<TeamScore> redTeam = getTeamScoreFromMatch(match, ColorEnum.RED);

        ScoreDTO scoreDTO = new ScoreDTO();
        calculateScore(blueTeam, redTeam, scoreDTO);

        return scoreDTO;
    }

    private List<TeamScore> getTeamScoreFromMatch(Match match, ColorEnum color) {
        return match.getRoundList().stream()
                .filter(Round::isRunning)
                .flatMap(round -> round.getTeamList().stream()
                        .filter(team -> team.getColor().getColorEnum().equals(color))
                        .flatMap(team -> team.getTeamScoreList().stream())).collect(Collectors.toList());
    }

    private void calculateScore(List<TeamScore> blueTeam, List<TeamScore> redTeam, ScoreDTO scoreDTO) {
        for(TeamScore score : blueTeam) {
            if(score.getGoalType().getType().equals(GoalTypeEnum.OWN_GOAL)) {
                scoreDTO.addRedTEam();
            } else {
                scoreDTO.addBlueTeam();
            }
        }
        for(TeamScore score : redTeam) {
            if(score.getGoalType().getType().equals(GoalTypeEnum.OWN_GOAL)) {
                scoreDTO.addBlueTeam();
            } else {
                scoreDTO.addRedTEam();
            }
        }
    }
}
