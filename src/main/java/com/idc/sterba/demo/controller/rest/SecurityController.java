package com.idc.sterba.demo.controller.rest;

import com.idc.sterba.demo.service.SecurityService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/security")
public class SecurityController {

    private SecurityService securityService;

    public SecurityController(SecurityService securityService) {
        this.securityService = securityService;
    }
}
