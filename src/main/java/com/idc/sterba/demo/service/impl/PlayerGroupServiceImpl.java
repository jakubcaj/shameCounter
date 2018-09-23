package com.idc.sterba.demo.service.impl;

import com.idc.sterba.demo.entity.PlayerGroup;
import com.idc.sterba.demo.repository.PlayerGroupRepository;
import com.idc.sterba.demo.service.PlayerGroupService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerGroupServiceImpl implements PlayerGroupService {

    private PlayerGroupRepository playerGroupRepository;

    public PlayerGroupServiceImpl(PlayerGroupRepository playerGroupRepository) {
        this.playerGroupRepository = playerGroupRepository;
    }

    @Override
    public PlayerGroup getPlayerGroupById(Long id) {
        return playerGroupRepository.getOne(id);
    }

    @Override
    public List<PlayerGroup> getPlayerGroupsByLeader(Long leaderId) {
        return this.playerGroupRepository.getAllByLeader_Id(leaderId);
    }
}
