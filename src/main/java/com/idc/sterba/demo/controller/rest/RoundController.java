package com.idc.sterba.demo.controller.rest;

import com.idc.sterba.demo.dto.JSONResponse;
import com.idc.sterba.demo.dto.MatchDTO;
import com.idc.sterba.demo.service.RoundService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
}
