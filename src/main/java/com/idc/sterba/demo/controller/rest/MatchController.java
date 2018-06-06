package com.idc.sterba.demo.controller.rest;

import com.idc.sterba.demo.dto.JSONResponse;
import com.idc.sterba.demo.dto.MatchDTO;
import com.idc.sterba.demo.entity.Match;
import com.idc.sterba.demo.entity.MatchDraft;
import com.idc.sterba.demo.service.MatchDraftService;
import com.idc.sterba.demo.service.MatchService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/match")
public class MatchController {

    private MatchService matchService;
    private MatchDraftService matchDraftService;

    public MatchController(MatchService matchService, MatchDraftService matchDraftService) {
        this.matchService = matchService;
        this.matchDraftService = matchDraftService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public JSONResponse createMatch(@RequestBody(required = false) MatchDTO matchDTO) {
        JSONResponse jsonResponse = new JSONResponse();
        Match match = matchService.saveMatch(matchDTO);
        jsonResponse.setObject(match);
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

    @RequestMapping(value = "/draft/position/update/{matchDraftId}", method = RequestMethod.POST)
    public JSONResponse updateMatchDraftPosition(@RequestBody MatchDTO matchDTO, @PathVariable Long matchDraftId) {
        matchDraftService.updateMatchDraft(matchDraftId, matchDTO);
        return new JSONResponse(null, true);
    }


}
