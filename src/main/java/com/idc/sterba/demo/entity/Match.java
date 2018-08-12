package com.idc.sterba.demo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.idc.sterba.demo.dto.MatchDTO;
import com.idc.sterba.demo.entity.enums.ColorEnum;
import com.idc.sterba.demo.entity.enums.PlayerRoleEnum;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "matchIdSeq")
    @SequenceGenerator(name = "matchIdSeq", sequenceName = "match_id_seq", allocationSize = 1)
    private Long id;

    @OneToMany(mappedBy = "match", cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private List<Round> roundList;

    @Column
    private boolean finished = false;

    public Match() {
    }

    public Match(MatchDTO matchDTO) {
        Round round = new Round(this, matchDTO);
        this.roundList = List.of(round);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Round> getRoundList() {
        return roundList;
    }

    public void setRoundList(List<Round> roundList) {
        this.roundList = roundList;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public Employee getPlayer(PlayerRoleEnum playerRole, ColorEnum color) {
        return roundList.stream().filter(Round::isRunning).map(round -> {
            return round.getTeamList().stream().filter(team -> team.getColor().equals(color)).map(team -> {
                return team.getTeamPlayerList().stream().filter(teamPlayer -> teamPlayer.getPlayerRole().equals(playerRole))
                        .map(TeamPlayer::getEmployee).collect(Collectors.toList()).get(0);
            }).collect(Collectors.toList()).get(0);
        }).collect(Collectors.toList()).get(0);
    }



}
