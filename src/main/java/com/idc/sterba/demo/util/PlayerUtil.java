package com.idc.sterba.demo.util;

import com.idc.sterba.demo.entity.Employee;
import com.idc.sterba.demo.entity.Team;
import com.idc.sterba.demo.entity.enums.PlayerRoleEnum;

import java.util.stream.Collectors;

public final class PlayerUtil {

    public static Employee getEmployeeFromTeam(Team team, PlayerRoleEnum playerRoleEnum) {
        return team.getTeamPlayerList().stream()
                .filter(x -> x.getPlayerRole().equals(playerRoleEnum)).collect(Collectors.toList()).get(0).getEmployee();
    }
}
