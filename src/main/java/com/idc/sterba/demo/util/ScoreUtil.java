package com.idc.sterba.demo.util;

import com.idc.sterba.demo.dto.ScoreDTO;
import com.idc.sterba.demo.entity.Match;
import com.idc.sterba.demo.entity.Round;
import com.idc.sterba.demo.entity.TeamScore;
import com.idc.sterba.demo.entity.enums.ColorEnum;
import com.idc.sterba.demo.entity.enums.GoalTypeEnum;

import java.util.List;
import java.util.stream.Collectors;

public final class ScoreUtil {

    public static ScoreDTO getScoreForRunningRound(Match match) {
        if (match == null) {
            return null;
        }
        return ScoreUtil.getScore(match);
    }

    public static ScoreDTO getScoreForMatch(Match match) {
        if (match == null) {
            return null;
        }

        return ScoreUtil.getScore(match.getRoundList());
    }

    private static ScoreDTO getScore(List<Round> roundList) {
        ScoreDTO scoreResult = new ScoreDTO();

        for (Round round : roundList.stream().filter(round -> !round.isRunning()).collect(Collectors.toList())) {
            List<TeamScore> blueTeam = getTeamScoreFromRound(round, ColorEnum.BLUE);
            List<TeamScore> redTeam = getTeamScoreFromRound(round, ColorEnum.RED);

            ScoreDTO scoreDTO = new ScoreDTO();
            calculateScore(blueTeam, redTeam, scoreDTO);

            if (scoreDTO.getBlueTeam() > scoreDTO.getRedTeam()) {
                scoreResult.addBlueTeam();
            } else if (scoreDTO.getRedTeam() > scoreDTO.getBlueTeam()) {
                scoreResult.addRedTEam();
            }

        }

        return scoreResult;
    }

    private static ScoreDTO getScore(Match match) {

        List<TeamScore> blueTeam = getTeamScoreFromMatch(match, ColorEnum.BLUE);
        List<TeamScore> redTeam = getTeamScoreFromMatch(match, ColorEnum.RED);

        ScoreDTO scoreDTO = new ScoreDTO();
        calculateScore(blueTeam, redTeam, scoreDTO);

        return scoreDTO;
    }

    private static List<TeamScore> getTeamScoreFromMatch(Match match, ColorEnum color) {
        Round round = match.getRoundList().stream()
                .filter(Round::isRunning).collect(Collectors.toList()).get(0);
        return ScoreUtil.getTeamScoreFromRound(round, color);
    }

    private static List<TeamScore> getTeamScoreFromRound(Round round, ColorEnum color) {
        return round.getTeamList().stream()
                        .filter(team -> team.getColor().equals(color))
                        .flatMap(team -> team.getTeamScoreList().stream()).collect(Collectors.toList());
    }

    private static void calculateScore(List<TeamScore> blueTeam, List<TeamScore> redTeam, ScoreDTO scoreDTO) {
        for(TeamScore score : blueTeam) {
            if(score.getGoalType().equals(GoalTypeEnum.OWN_GOAL)) {
                scoreDTO.addRedTEam();
            } else {
                scoreDTO.addBlueTeam();
            }
        }
        for(TeamScore score : redTeam) {
            if(score.getGoalType().equals(GoalTypeEnum.OWN_GOAL)) {
                scoreDTO.addBlueTeam();
            } else {
                scoreDTO.addRedTEam();
            }
        }
    }
}
