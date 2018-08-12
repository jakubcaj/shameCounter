package com.idc.sterba.demo.controller.rest;

import com.idc.sterba.demo.dto.JSONResponse;
import com.idc.sterba.demo.dto.MatchDTO;
import com.idc.sterba.demo.service.RoundService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/round")
public class RoundController {
    private RoundService roundService;

    public RoundController(RoundService roundService) {
        this.roundService = roundService;
    }

    @RequestMapping(value = "/finish/{matchId}", method = RequestMethod.POST)
    public JSONResponse finishRound(@PathVariable("matchId") Long matchId) {
        MatchDTO matchDTO = roundService.finishRound(matchId);
        return new JSONResponse(matchDTO, true);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public JSONResponse updateRound(@RequestBody MatchDTO matchDTO) {

        return new JSONResponse(roundService.updateRound(matchDTO));
    }

    @RequestMapping(value = "/current/score", method = RequestMethod.POST)
    public JSONResponse updateRound(@RequestBody Long matchId) {

        return new JSONResponse(roundService.getScoreOfRound(matchId));
    }
}
