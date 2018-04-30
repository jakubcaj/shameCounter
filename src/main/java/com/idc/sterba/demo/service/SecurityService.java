package com.idc.sterba.demo.service;

import com.idc.sterba.demo.entity.Employee;

public interface SecurityService {

    boolean isUserAuthenticated();

    Employee getLoggedUser();

    String hashPassword(String password);
}
