package com.idc.sterba.demo.service;

import com.idc.sterba.demo.entity.PlayerGroup;

import java.util.List;

public interface PlayerGroupService {

    PlayerGroup getPlayerGroupById(Long id);

    List<PlayerGroup> getPlayerGroupsByLeader(Long leaderId);
}
