package com.idc.sterba.demo.controller;

import com.idc.sterba.demo.dto.GroupDTO;
import com.idc.sterba.demo.dto.JSONResponse;
import com.idc.sterba.demo.service.EmployeeService;
import com.idc.sterba.demo.service.PlayerGroupService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    private EmployeeService employeeService;
    private PlayerGroupService playerGroupService;

    public WebSocketController(EmployeeService employeeService, PlayerGroupService playerGroupService) {
        this.employeeService = employeeService;
        this.playerGroupService = playerGroupService;
    }

    @MessageMapping("/team/{groupId}/{userId}")
    @SendTo("/team/{groupId}")
    public JSONResponse onGroupInvite(@DestinationVariable Long groupId, @DestinationVariable Long userId) {
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setEmployee(employeeService.getEmployeeById(userId));
        groupDTO.setPlayerGroup(playerGroupService.getPlayerGroupById(groupId));
        return new JSONResponse(groupDTO, true);
    }
}
