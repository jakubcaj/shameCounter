package com.idc.sterba.demo.entity;

import com.idc.sterba.demo.dto.MatchDTO;
import org.eclipse.persistence.annotations.Cache;

import javax.persistence.*;
import java.util.List;

@Entity
@Cache(shared = false)
public class MatchDraft {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "matchDraftIdSeq")
    @SequenceGenerator(name = "matchDraftIdSeq", sequenceName = "match_draft_id_seq", allocationSize = 1)
    private Long id;

    @OneToMany(cascade = CascadeType.REFRESH)
    private List<Employee> invitedPlayerList;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Employee blueGoalman;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Employee blueAttacker;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Employee redGoalman;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Employee redAttacker;

    public MatchDraft() {

    }

    public MatchDraft(MatchDTO matchDTO) {
        this.blueGoalman = matchDTO.getBlueGoalman();
        this.blueAttacker = matchDTO.getBlueAttacker();
        this.redGoalman = matchDTO.getRedGoalman();
        this.redAttacker = matchDTO.getRedAttacker();
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

    public Employee getBlueGoalman() {
        return blueGoalman;
    }

    public void setBlueGoalman(Employee blueGoalman) {
        this.blueGoalman = blueGoalman;
    }

    public Employee getBlueAttacker() {
        return blueAttacker;
    }

    public void setBlueAttacker(Employee blueAttacker) {
        this.blueAttacker = blueAttacker;
    }

    public Employee getRedGoalman() {
        return redGoalman;
    }

    public void setRedGoalman(Employee redGoalman) {
        this.redGoalman = redGoalman;
    }

    public Employee getRedAttacker() {
        return redAttacker;
    }

    public void setRedAttacker(Employee redAttacker) {
        this.redAttacker = redAttacker;
    }
}
