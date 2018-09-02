package com.idc.sterba.demo.entity.report;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "match_win_count_report",schema = "report")
public class MatchWinCountReport {
    @Id
    private Long id;

    private String firstname;

    private String lastname;

    @Column(name = "win_count")
    private Long winCount;

    @Column(name = "match_count")
    private Long matchCount;

    public Long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Long getWinCount() {
        return winCount;
    }

    public Long getMatchCount() {
        return matchCount;
    }
}
