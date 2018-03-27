package com.idc.sterba.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teamIdSeq")
    @SequenceGenerator(name = "teamIdSeq", sequenceName = "team_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JsonBackReference
    private Round round;

    @OneToMany(mappedBy = "team", cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private List<TeamScore> teamScoreList;

    @OneToMany(mappedBy = "team", cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private List<TeamPlayer> teamPlayerList;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Color color;

    public Team() {

    }

    public Team(Employee goalman, Employee attacker, Round round, ColorEnum colorEnum) {
        TeamPlayer goalManPlayer = new TeamPlayer(goalman, PlayerRoleEnum.GOALMAN, this);
        TeamPlayer attackerPlayer = new TeamPlayer(attacker, PlayerRoleEnum.ATTACKER, this);

        this.teamPlayerList = List.of(goalManPlayer, attackerPlayer);
        this.round = round;
        this.color = new Color(colorEnum);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<TeamScore> getTeamScoreList() {
        return teamScoreList;
    }

    public void setTeamScoreList(List<TeamScore> teamScoreList) {
        this.teamScoreList = teamScoreList;
    }

    public List<TeamPlayer> getTeamPlayerList() {
        return teamPlayerList;
    }

    public void setTeamPlayerList(List<TeamPlayer> teamPlayerList) {
        this.teamPlayerList = teamPlayerList;
    }

    public Round getRound() {
        return round;
    }

    public void setRound(Round round) {
        this.round = round;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
