package com.idc.sterba.demo.service.impl;

import com.idc.sterba.demo.entity.Employee;
import com.idc.sterba.demo.entity.PlayerGroup;
import com.idc.sterba.demo.repository.EmployeeRepository;
import com.idc.sterba.demo.service.SecurityService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecurityServiceImpl implements SecurityService {

    private final EmployeeRepository employeeRepository;
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public SecurityServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee getLoggedUser() {
        return employeeRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @Override
    public List<PlayerGroup> getLoggedUserGroups() {
        return getLoggedUser().getPlayerGroupList();
    }

    @Override
    public boolean isUserAuthenticated() {
        return !SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ANONYMOUS"));
    }

    @Override
    public String hashPassword(String password) {
        return SecurityServiceImpl.encoder.encode(password);
    }

    @Override
    public boolean passwordMatches(String rawPassword, String encodedPassword) {
        return SecurityServiceImpl.encoder.matches(rawPassword, encodedPassword);
    }
}
