package com.idc.sterba.demo.dto;

import com.idc.sterba.demo.entity.Employee;

public class MatchDTO {

    private Employee blueGoalman;
    private Employee blueAttacker;
    private Employee redGoalman;
    private Employee redAttacker;

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
