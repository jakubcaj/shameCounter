package com.idc.sterba.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.eclipse.persistence.annotations.Cache;

import javax.persistence.*;

@Entity(name = "player_group")
@Cache(shared = false)
public class PlayerGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "playerGroupIdSeq")
    @SequenceGenerator(name = "playerGroupIdSeq", sequenceName = "player_group_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToOne
    @JsonIgnore
    private Employee leader;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee getLeader() {
        return leader;
    }

    public void setLeader(Employee leader) {
        this.leader = leader;
    }
}
