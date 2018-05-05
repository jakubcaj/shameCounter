package com.idc.sterba.demo.service.impl;

import com.idc.sterba.demo.entity.PlayerGroup;
import com.idc.sterba.demo.repository.PlayerGroupRepository;
import com.idc.sterba.demo.service.SecurityService;
import com.idc.sterba.demo.service.PlayerGroupService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerGroupServiceImpl implements PlayerGroupService {

    private SecurityService securityService;
    private PlayerGroupRepository playerGroupRepository;

    public PlayerGroupServiceImpl(SecurityService securityService, PlayerGroupRepository playerGroupRepository) {
        this.securityService = securityService;
        this.playerGroupRepository = playerGroupRepository;
    }

    @Override
    public List<PlayerGroup> getLoggedUserGroups() {
        return securityService.getLoggedUser().getPlayerGroup();
    }

    @Override
    public PlayerGroup getPlayerGroupById(Long id) {
        return playerGroupRepository.getOne(id);
    }
}
