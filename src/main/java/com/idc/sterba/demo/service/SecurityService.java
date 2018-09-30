package com.idc.sterba.demo.service;

import com.idc.sterba.demo.entity.Employee;
import com.idc.sterba.demo.entity.PlayerGroup;

import java.util.List;

public interface SecurityService {

    boolean isUserAuthenticated();

    Employee getLoggedUser();

    String hashPassword(String password);

    List<PlayerGroup> getLoggedUserGroups();

    boolean canUserSeeAdminPage();

    boolean passwordMatches(String rawPassword, String encodedPassword);
}
