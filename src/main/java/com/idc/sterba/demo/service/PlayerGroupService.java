package com.idc.sterba.demo.service;

import com.idc.sterba.demo.entity.PlayerGroup;

import java.util.List;

public interface PlayerGroupService {

    List<PlayerGroup> getLoggedUserGroups();

    PlayerGroup getPlayerGroupById(Long id);
}
