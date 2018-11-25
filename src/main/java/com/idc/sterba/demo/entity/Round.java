package com.idc.sterba.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.idc.sterba.demo.dto.MatchDTO;
import com.idc.sterba.demo.entity.enums.ColorEnum;
import com.idc.sterba.demo.entity.enums.PlayerRoleEnum;
import com.idc.sterba.demo.util.PlayerUtil;
import org.eclipse.persistence.annotations.Cache;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Cache(shared = false)
public class Round {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roundIdSeq")
    @SequenceGenerator(name = "roundIdSeq", sequenceName = "round_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JsonBackReference
    private Match match;

    @OneToMany(mappedBy = "round", cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private List<Team> teamList;

    @Column
    private Boolean running = true;

    public Round() {
    }

    public Round(Match match, MatchDTO matchDTO) {
        Team blueTeam = new Team(matchDTO.getBlueGoalman(), matchDTO.getBlueAttacker(), this, ColorEnum.BLUE);
        Team redTeam = new Team(matchDTO.getRedGoalman(), matchDTO.getRedAttacker(), this, ColorEnum.RED);

        this.match = match;
        this.teamList = List.of(blueTeam, redTeam);
    }

    public Round(Round round) {
        this.match = round.getMatch();
        this.match.getRoundList().add(this);
        Team tBlueTeam = getTeam(round, ColorEnum.BLUE);
        Team tRedTeam = getTeam(round, ColorEnum.RED);

        Team blueTeam = new Team(PlayerUtil.getEmployeeFromTeam(tRedTeam, PlayerRoleEnum.GOALMAN),
                PlayerUtil.getEmployeeFromTeam(tRedTeam, PlayerRoleEnum.ATTACKER), this, ColorEnum.BLUE);
        Team redTeam = new Team(PlayerUtil.getEmployeeFromTeam(tBlueTeam, PlayerRoleEnum.GOALMAN),
                PlayerUtil.getEmployeeFromTeam(tBlueTeam, PlayerRoleEnum.ATTACKER), this, ColorEnum.RED);
        this.teamList = List.of(blueTeam, redTeam);
    }

    public void updateEmployeesPosition(MatchDTO matchDTO) {
        Team blueTeam = getTeam(this, ColorEnum.BLUE);
        Team redTeam = getTeam(this, ColorEnum.RED);

        blueTeam.updateEmployeePositions(matchDTO.getBlueGoalman(), matchDTO.getBlueAttacker());
        redTeam.updateEmployeePositions(matchDTO.getRedGoalman(), matchDTO.getRedAttacker());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public List<Team> getTeamList() {
        return teamList;
    }

    public void setTeamList(List<Team> teamList) {
        this.teamList = teamList;
    }

    public Boolean isRunning() {
        return running;
    }

    public void setRunning(Boolean running) {
        this.running = running;
    }

    private Team getTeam(Round round, ColorEnum colorEnum) {
        return round.getTeamList().stream()
                .filter(team -> team.getColor().equals(colorEnum))
                .collect(Collectors.toList()).get(0);
    }
}
