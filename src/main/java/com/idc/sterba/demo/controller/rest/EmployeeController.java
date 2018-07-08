package com.idc.sterba.demo.controller.rest;

import com.idc.sterba.demo.dto.JSONResponse;
import com.idc.sterba.demo.service.EmployeeService;
import com.idc.sterba.demo.service.PlayerGroupService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/employee")
public class EmployeeController {

    private PlayerGroupService teamService;
    private EmployeeService employeeService;


    public EmployeeController(PlayerGroupService teamService, EmployeeService employeeService) {
        this.teamService = teamService;
        this.employeeService = employeeService;
    }

    @RequestMapping(value = "/groups", method = RequestMethod.POST)
    public JSONResponse getUserTeams() {
        JSONResponse jsonResponse = new JSONResponse();
        jsonResponse.setObject(teamService.getLoggedUserGroups());
        jsonResponse.setSuccess(true);
        return jsonResponse;
    }

    @RequestMapping(value = "/searchByGroup", method = RequestMethod.POST)
    public JSONResponse getUsersByGroup(@RequestBody Long groupId) {
        JSONResponse jsonResponse = new JSONResponse();
        jsonResponse.setObject(employeeService.getEmployeesByGroup(groupId));
        return jsonResponse;
    }
}
