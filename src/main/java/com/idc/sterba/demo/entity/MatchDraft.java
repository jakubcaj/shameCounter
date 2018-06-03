package com.idc.sterba.demo.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class MatchDraft {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "matchDraftIdSeq")
    @SequenceGenerator(name = "matchDraftIdSeq", sequenceName = "match_draft_id_seq", allocationSize = 1)
    private Long id;

    @OneToMany(cascade = CascadeType.REFRESH)
    private List<Employee> invitedPlayerList;

    public MatchDraft() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Employee> getInvitedPlayerList() {
        return invitedPlayerList;
    }

    public void setInvitedPlayerList(List<Employee> invitedPlayerList) {
        this.invitedPlayerList = invitedPlayerList;
    }
}
