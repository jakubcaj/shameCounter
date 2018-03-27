package com.idc.sterba.demo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.idc.sterba.demo.dto.MatchDTO;

import javax.persistence.*;
import java.util.List;

@Entity
public class Match {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "matchIdSeq")
    @SequenceGenerator(name = "matchIdSeq", sequenceName = "match_id_seq", allocationSize = 1)
    private Long id;

    @OneToMany(mappedBy = "match", cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private List<Round> roundList;

    public Match() {}

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
}
