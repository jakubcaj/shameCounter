package com.idc.sterba.demo.controller;

import com.google.gwt.core.client.debug.JsoInspector;
import com.idc.sterba.demo.dto.GroupDTO;
import com.idc.sterba.demo.dto.JSONResponse;
import com.idc.sterba.demo.dto.MatchDTO;
import com.idc.sterba.demo.entity.MatchDraft;
import com.idc.sterba.demo.service.EmployeeService;
import com.idc.sterba.demo.service.MatchDraftService;
import com.idc.sterba.demo.service.PlayerGroupService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    private EmployeeService employeeService;
    private PlayerGroupService playerGroupService;
    private MatchDraftService matchDraftService;

    public WebSocketController(EmployeeService employeeService, PlayerGroupService playerGroupService, MatchDraftService matchDraftService) {
        this.employeeService = employeeService;
        this.playerGroupService = playerGroupService;
        this.matchDraftService = matchDraftService;
    }

    @MessageMapping("/team/{groupId}/{userId}/{matchId}")
    @SendTo("/team/{groupId}")
    public JSONResponse onGroupInvite(@DestinationVariable Long groupId, @DestinationVariable Long userId,
                                      @DestinationVariable Long matchId) {
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setEmployee(employeeService.getEmployeeById(userId));
        groupDTO.setPlayerGroup(playerGroupService.getPlayerGroupById(groupId));
        groupDTO.setMatchDraft(matchDraftService.getMatchDraftById(matchId));
        return new JSONResponse(groupDTO, true);
    }

    @MessageMapping("/match/draft/{matchDraftId}/{employeeId}")
    @SendTo("/match/draft/{matchDraftId}")
    public JSONResponse onInviteAccept(@DestinationVariable Long matchDraftId, @DestinationVariable Long employeeId) {
        MatchDraft matchDraft = matchDraftService.getMatchDraftById(matchDraftId);
        matchDraft.getInvitedPlayerList().add(employeeService.getEmployeeById(employeeId));
        matchDraftService.updateMatchDraft(matchDraft);

        return new JSONResponse(matchDraft, true);
    }

    @MessageMapping("/match/draft/position/change/{matchDraftId}")
    @SendTo("/match/draft/position/{matchDraftId}")
    public JSONResponse onPositionChange(MatchDTO matchDTO) {
        return new JSONResponse(matchDTO, true);
    }
}
