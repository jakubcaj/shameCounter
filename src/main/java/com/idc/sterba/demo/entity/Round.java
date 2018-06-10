package com.idc.sterba.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.idc.sterba.demo.dto.MatchDTO;
import com.idc.sterba.demo.util.PlayerUtil;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
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
        Team tBlueTeam = round.getTeamList().stream()
                .filter(team -> team.getColor().getColorEnum().equals(ColorEnum.BLUE))
                .collect(Collectors.toList()).get(0);
        Team tRedTeam = round.getTeamList().stream()
                .filter(team -> team.getColor().getColorEnum().equals(ColorEnum.RED))
                .collect(Collectors.toList()).get(0);

        Team blueTeam = new Team(PlayerUtil.getEmployeeFromTeam(tRedTeam, PlayerRoleEnum.GOALMAN),
                PlayerUtil.getEmployeeFromTeam(tRedTeam, PlayerRoleEnum.ATTACKER), this, ColorEnum.BLUE);
        Team redTeam = new Team(PlayerUtil.getEmployeeFromTeam(tBlueTeam, PlayerRoleEnum.GOALMAN),
                PlayerUtil.getEmployeeFromTeam(tBlueTeam, PlayerRoleEnum.ATTACKER), this, ColorEnum.RED);
        this.teamList = List.of(blueTeam, redTeam);
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
}
