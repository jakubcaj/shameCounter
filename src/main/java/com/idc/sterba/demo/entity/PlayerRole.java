package com.idc.sterba.demo.entity;

import javax.persistence.*;

@Entity
public class PlayerRole {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "playerRoleIdSeq")
    @SequenceGenerator(name = "playerRoleIdSeq", sequenceName = "player_role_id_seq", allocationSize = 1)
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private PlayerRoleEnum playerRole;

    public PlayerRole() {}

    public PlayerRole(PlayerRoleEnum playerRoleEnum) {
        this.playerRole = playerRoleEnum;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PlayerRoleEnum getPlayerRole() {
        return playerRole;
    }

    public void setPlayerRole(PlayerRoleEnum playerRole) {
        this.playerRole = playerRole;
    }
}

enum PlayerRoleEnum {
    ATTACKER,
    GOALMAN
}
