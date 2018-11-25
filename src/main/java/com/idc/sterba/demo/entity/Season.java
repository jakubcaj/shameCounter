package com.idc.sterba.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.idc.sterba.demo.entity.enums.SeasonEnum;
import org.eclipse.persistence.annotations.Cache;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Cache(shared = false)
public class Season {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seasonIdSeq")
    @SequenceGenerator(name = "seasonIdSeq", sequenceName = "season_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "season_name")
    @Enumerated(EnumType.STRING)
    private SeasonEnum season;

    @OneToMany(mappedBy = "season")
    @JsonBackReference
    private List<Match> matchList;

    @Column(name = "started_at")
    private LocalDateTime startedAt;

    @Column
    private Boolean active;

    public Season() {

    }

    public Season(SeasonEnum season) {
        this.startedAt = LocalDateTime.now();
        this.active = true;
        this.season = season;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SeasonEnum getSeason() {
        return season;
    }

    public void setSeason(SeasonEnum season) {
        this.season = season;
    }

    public List<Match> getMatchList() {
        return matchList;
    }

    public void setMatchList(List<Match> matchList) {
        this.matchList = matchList;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
