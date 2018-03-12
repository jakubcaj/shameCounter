package com.idc.sterba.demo.entity;

import javax.persistence.*;

@Entity
public class Team {
    @Id
    @SequenceGenerator(name = "team_id_seq")
    private Long id;

    @OneToOne
    private Employee p1;

    @OneToOne
    private Employee p2;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getP1() {
        return p1;
    }

    public void setP1(Employee p1) {
        this.p1 = p1;
    }

    public Employee getP2() {
        return p2;
    }

    public void setP2(Employee p2) {
        this.p2 = p2;
    }
}
