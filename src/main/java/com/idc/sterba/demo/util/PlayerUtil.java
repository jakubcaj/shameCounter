package com.idc.sterba.demo.util;

import com.idc.sterba.demo.dto.MatchDTO;
import com.idc.sterba.demo.entity.Employee;
import com.idc.sterba.demo.entity.Match;
import com.idc.sterba.demo.entity.Team;
import com.idc.sterba.demo.entity.enums.ColorEnum;
import com.idc.sterba.demo.entity.enums.PlayerRoleEnum;

import java.util.stream.Collectors;

public final class PlayerUtil {

    public static Employee getEmployeeFromTeam(Team team, PlayerRoleEnum playerRoleEnum) {
        return team.getTeamPlayerList().stream()
                .filter(x -> x.getPlayerRole().equals(playerRoleEnum)).collect(Collectors.toList()).get(0).getEmployee();
    }

    public static MatchDTO createMatchDTOFromMatch(Match match) {
        MatchDTO matchDTO = new MatchDTO();
        matchDTO.setBlueGoalman(match.getPlayer(PlayerRoleEnum.GOALMAN, ColorEnum.BLUE));
        matchDTO.setBlueAttacker(match.getPlayer(PlayerRoleEnum.ATTACKER, ColorEnum.BLUE));
        matchDTO.setRedGoalman(match.getPlayer(PlayerRoleEnum.GOALMAN, ColorEnum.RED));
        matchDTO.setRedAttacker(match.getPlayer(PlayerRoleEnum.ATTACKER, ColorEnum.RED));
        matchDTO.setMatchId(match.getId());
        return matchDTO;
    }
}
