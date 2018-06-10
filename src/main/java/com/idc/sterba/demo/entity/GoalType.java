package com.idc.sterba.demo.entity;

import javax.persistence.*;

@Entity
public class GoalType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "goalTypeIdSeq")
    @SequenceGenerator(name = "goalTypeIdSeq", sequenceName = "goal_type_id_seq", allocationSize = 1)
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private GoalTypeEnum type;

    public GoalType() {
    }

    public GoalType(GoalTypeEnum type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GoalTypeEnum getType() {
        return type;
    }

    public void setType(GoalTypeEnum type) {
        this.type = type;
    }
}

