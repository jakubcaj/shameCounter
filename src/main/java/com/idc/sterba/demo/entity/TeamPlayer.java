package com.idc.sterba.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.idc.sterba.demo.entity.enums.PlayerRoleEnum;
import org.eclipse.persistence.annotations.Cache;

import javax.persistence.*;

@Entity
@Cache(shared = false)
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

    @Column(name = "player_role")
    @Enumerated(EnumType.STRING)
    private PlayerRoleEnum playerRole;

    public TeamPlayer() {}

    public TeamPlayer(Employee employee, PlayerRoleEnum playerRoleEnum, Team team) {
        this.team = team;
        this.employee = employee;
        this.playerRole = playerRoleEnum;
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

    public PlayerRoleEnum getPlayerRole() {
        return playerRole;
    }

    public void setPlayerRole(PlayerRoleEnum playerRole) {
        this.playerRole = playerRole;
    }
}
