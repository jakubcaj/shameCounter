package com.idc.sterba.demo.controller.rest;

import com.idc.sterba.demo.dto.EmployeeWithCoeficientDTO;
import com.idc.sterba.demo.dto.JSONResponse;
import com.idc.sterba.demo.service.EmployeeService;
import com.idc.sterba.demo.service.GroupService;
import com.idc.sterba.demo.service.PlayerGroupService;
import com.idc.sterba.demo.service.SecurityService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/group")
public class GroupController {

    private EmployeeService employeeService;
    private GroupService groupService;
    private PlayerGroupService playerGroupService;
    private SecurityService securityService;

    public GroupController(EmployeeService employeeService, GroupService groupService, PlayerGroupService playerGroupService,
                           SecurityService securityService) {
        this.employeeService = employeeService;
        this.groupService = groupService;
        this.playerGroupService = playerGroupService;
        this.securityService = securityService;
    }

    @RequestMapping(value = "/coeficient/{groupId}", method = RequestMethod.POST)
    public JSONResponse getEmployeesWithCoeficient(@PathVariable("groupId") Long groupId) {

        return new JSONResponse(employeeService.getEmployeesByGroup(groupId).stream()
                .map(e -> new EmployeeWithCoeficientDTO(e, groupId)).collect(Collectors.toList()));
    }

    @RequestMapping(value = "/coeficient/update/{groupId}", method = RequestMethod.POST)
    public JSONResponse updateCoeficientForEmployees(@PathVariable("groupId") Long groupId,
                                                     @RequestBody List<EmployeeWithCoeficientDTO> employeeWithCoeficientDTOList) {
        groupService.updateCoeficientForEmployees(employeeWithCoeficientDTOList, groupId);
        return new JSONResponse(null, true);
    }

    @RequestMapping(value = "/leader", method = RequestMethod.POST)
    public JSONResponse getPlayerGroupsByLoggedUserLeader() {
        return new JSONResponse(playerGroupService.getPlayerGroupsByLeader(securityService.getLoggedUser().getId()), true);
    }
}
