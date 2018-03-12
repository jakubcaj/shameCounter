package com.idc.sterba.demo.entity;

import javax.persistence.*;

@Entity
public class Match {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    @SequenceGenerator(name = "match_id_seq")
    private Long id;

    @OneToOne(optional = false)
    private Team team1;

    @OneToOne(optional = false)
    private Team team2;

    @Column
    private int goals1;

    @Column
    private int goals2;

    public Match() {
        if (getTeam1() == null) {
            setTeam1(new Team());
        }
        if (getTeam2() == null) {
            setTeam1(new Team());
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    public int getGoals1() {
        return goals1;
    }

    public void setGoals1(int goals1) {
        this.goals1 = goals1;
    }

    public int getGoals2() {
        return goals2;
    }

    public void setGoals2(int goals2) {
        this.goals2 = goals2;
    }
}
