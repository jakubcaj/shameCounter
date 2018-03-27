package com.idc.sterba.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class TeamPlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teamPlayerIdSeq")
    @SequenceGenerator(name = "teamPlayerIdSeq", sequenceName = "team_player_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JsonBackReference
    private Team team;

    @OneToOne
    private Employee employee;

    @OneToOne(cascade = CascadeType.PERSIST)
    private PlayerRole playerRole;

    public TeamPlayer() {}

    public TeamPlayer(Employee employee, PlayerRoleEnum playerRoleEnum, Team team) {
        this.team = team;
        this.employee = employee;
        this.playerRole = new PlayerRole(playerRoleEnum);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public PlayerRole getPlayerRole() {
        return playerRole;
    }

    public void setPlayerRole(PlayerRole playerRole) {
        this.playerRole = playerRole;
    }
}
