package com.idc.sterba.demo.controller.rest;

import com.idc.sterba.demo.dto.JSONResponse;
import com.idc.sterba.demo.dto.MatchDTO;
import com.idc.sterba.demo.dto.PlayerGoalDTO;
import com.idc.sterba.demo.entity.MatchDraft;
import com.idc.sterba.demo.entity.TeamScore;
import com.idc.sterba.demo.service.MatchDraftService;
import com.idc.sterba.demo.service.MatchService;
import com.idc.sterba.demo.service.RoundService;
import com.idc.sterba.demo.service.TeamScoreService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/match")
public class MatchController {

    private MatchService matchService;
    private RoundService roundService;
    private MatchDraftService matchDraftService;
    private TeamScoreService teamScoreService;

    public MatchController(MatchService matchService, MatchDraftService matchDraftService,
                           RoundService roundService, TeamScoreService teamScoreService) {
        this.matchService = matchService;
        this.matchDraftService = matchDraftService;
        this.roundService = roundService;
        this.teamScoreService = teamScoreService;
    }

    @RequestMapping(value = "/{matchId}", method = RequestMethod.POST)
    public JSONResponse getMatch(@PathVariable("matchId") Long matchId) {
        return new JSONResponse(matchService.getMatchDto(matchId));
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public JSONResponse createMatch(@RequestBody(required = false) MatchDTO matchDTO) {
        JSONResponse jsonResponse = new JSONResponse();
        matchService.saveMatch(matchDTO);
        jsonResponse.setObject(matchDTO);
        return jsonResponse;
    }

    @RequestMapping(value = "/draft/create", method = RequestMethod.POST)
    public JSONResponse createMatchDraft(@RequestBody MatchDraft matchDraft) {
        JSONResponse jsonResponse = new JSONResponse();
        matchDraftService.createMatchDraft(matchDraft);
        jsonResponse.setObject(matchDraft);
        return jsonResponse;
    }

    @RequestMapping(value = "/draft/update", method = RequestMethod.POST)
    public JSONResponse updateMatchDraft(@RequestBody MatchDraft matchDraft) {
        JSONResponse jsonResponse = new JSONResponse();
        matchDraftService.updateMatchDraft(matchDraft);
        jsonResponse.setObject(matchDraft);
        return jsonResponse;
    }

    @RequestMapping(value = "/draft", method = RequestMethod.POST)
    public JSONResponse getMatchDraftById(@RequestBody Long id) {
        JSONResponse jsonResponse = new JSONResponse();
        jsonResponse.setObject(matchDraftService.getMatchDraftById(id));
        return jsonResponse;
    }

    @RequestMapping(value = "/player/goal", method = RequestMethod.POST)
    public JSONResponse savePlayerGoal(@RequestBody PlayerGoalDTO playerGoalDTO) {
        TeamScore teamScore = this.matchService.saveGoal(playerGoalDTO);

        return new JSONResponse(teamScore);
    }

    @RequestMapping(value = "/unfinished", method = RequestMethod.POST)
    public JSONResponse getAllUnfinishedMatches() {
        return new JSONResponse(matchService.getAllUnfinishedMatches());
    }

    @RequestMapping(value = "/score", method = RequestMethod.POST)
    public JSONResponse getMatchScore(@RequestBody Long matchId) {
        return new JSONResponse(matchService.getScore(matchId));
    }

    @RequestMapping(value = "/finish", method = RequestMethod.POST)
    public JSONResponse finishMatch(@RequestBody Long matchId) {
        this.matchService.finishMatch(matchId);
        return new JSONResponse();
    }

    @RequestMapping(value = "/{matchId}/teamScore", method = RequestMethod.POST)
    public JSONResponse getAllTeamScore(@PathVariable("matchId") Long matchId) {
        return new JSONResponse(this.teamScoreService.getAllTeamScore(matchId));
    }

    @RequestMapping(value = "/revert/score/{teamScoreId}", method = RequestMethod.POST)
    public JSONResponse revertTeamScore(@PathVariable("teamScoreId") Long teamScoreId) {
        return new JSONResponse(this.teamScoreService.removeTeamScore(teamScoreId));
    }
}
