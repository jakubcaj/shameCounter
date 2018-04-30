package com.idc.sterba.demo.service.impl;

import com.idc.sterba.demo.entity.secure.Role;
import com.idc.sterba.demo.repository.RoleRepository;
import com.idc.sterba.demo.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getRoleById(Long id) {
        return roleRepository.getOne(id);
    }
}
